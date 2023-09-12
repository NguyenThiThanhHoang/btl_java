/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.formatters.LocationFormatter;
import com.apjob.pojo.Candidate;
import com.apjob.pojo.Company;
import com.apjob.pojo.Employer;
import com.apjob.pojo.Location;
import com.apjob.pojo.User;
import com.apjob.repository.CandidateRepository;
import com.apjob.repository.CompanyRepository;
import com.apjob.repository.EmployerRepository;
import com.apjob.repository.LocationRepository;
import com.apjob.repository.UserRepository;
import com.apjob.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmployerRepository emRepo;

    @Autowired
    private CandidateRepository caRepo;

    @Autowired
    private CompanyRepository comRepo;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private LocationRepository loRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByEmail(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPassword(), authorities);
    }

    @Override
    public List<User> getAdmins(Map<String, String> params) {
        return this.userRepo.getUsers();
    }

    @Override
    public int countAdmins() {
        return this.userRepo.countUsers();
    }

    @Override
    public boolean addOrUpdateUser(User u) {
        Company company = new Company();
        Employer employer = new Employer();
        Candidate candidate = new Candidate();
        Boolean addResult = false;

        if (!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!u.getFileCompany().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFileCompany().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                company.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String pass = u.getPassword();
        u.setPassword(this.passEncoder.encode(pass));
        Boolean addUserResult = userRepo.addOrUpdateUser(u);
        int userId = u.getId();

        if (u.getUserRole().toString().equals("CANDIDATE")) {
            candidate.setId(userId);
            candidate.setLocation(u.getLocation());
            candidate.setSchoolName(u.getSchoolName());
            candidate.setBirthDay(u.getBirthDay());
            addResult = caRepo.addOrUpdateCandidate(candidate);
            return addResult;
        } else if (u.getUserRole().toString().equals("EMPLOYER")) {
            company.setNameCompany(u.getNameCompany());
            company.setAddress(u.getAddress());
            company.setTax(u.getTax());
            company.setEmailCompany(u.getEmailCompany());
            company.setPhoneCompany(u.getPhoneCompany());
            company.setDescription(u.getDescription());
            comRepo.addOrUpdateCompany(company);

            employer.setId(userId);
            employer.setCompany(company);
            addResult = emRepo.addOrUpdateEmployer(employer);
            return addResult;
        } else {
            return addUserResult;
        }
    }

    @Override
    public List<Employer> getEmployers(Map<String, String> params) {
        return this.emRepo.getEmployers(params);
    }

    @Override
    public int countEmployers() {
        return this.emRepo.countEmployers();
    }

    @Override
    public List<Candidate> getCandidates(Map<String, String> params) {
        return this.caRepo.getCandidates(params);
    }

    @Override
    public int countCandidates() {
        return this.caRepo.countCandidates();
    }

    @Override
    public List<Company> getCompanys(Map<String, String> params) {
        return this.comRepo.getCompanys(params);
    }

    @Override
    public int countCompanyes() {
        return this.comRepo.countCompanys();
    }

    @Override
    public User getUserByUn(String username) {
        return this.userRepo.getUserByEmail(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public User addOrUpdateUserApi(Map<String, String> params, MultipartFile avatar, MultipartFile avatarCompany, int userId) {
        Company company = new Company();
        Employer employer = new Employer();
        Candidate candidate = new Candidate();
        Location location = new Location();
        User user = new User();
        Boolean addResult = false;
        
        //!= -1 là update
        if (userId != -1){
            user.setId(userId);
            candidate.setId(userId);
            employer.setId(userId);
            company.setId(this.emRepo.getEmployerById(userId).getCompany().getId());
        }

        user.setName(params.get("name"));
        user.setEmail(params.get("username"));
        user.setPassword(this.passEncoder.encode(params.get("password")));
        user.setPhone(params.get("phone"));
        user.setUserRole(params.get("userRole"));

        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (user.getUserRole() == "CANDIDATE") {
            user.setActive(true);
            addResult = userRepo.addOrUpdateUser(user);
            if (addResult) {
                candidate.setId(user.getId());
                candidate.setSchoolName(params.get("schoolName"));
                String dateString = params.get("birthday");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                try {
                    Date utilDate = dateFormat.parse(dateString);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    candidate.setBirthDay(sqlDate);

                } catch (ParseException ex) {
                    candidate.setBirthDay(java.sql.Date.valueOf(LocalDate.now()));
                    Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                location = this.loRepo.getLocationById(Integer.parseInt(params.get("locationId")));
                candidate.setLocation(location);
                caRepo.addOrUpdateCandidate(candidate);
                return user;
            } else {
                return user;
            }

        } else if (user.getUserRole() == "EMPLOYER") {
            user.setActive(false);
            addResult = userRepo.addOrUpdateUser(user);
            if (addResult) {
                company.setNameCompany(params.get("nameCompany"));
                company.setAddress(params.get("address"));
                company.setTax(Integer.parseInt(params.get("tax")));
                company.setEmailCompany(params.get("emailCommpany"));
                company.setPhoneCompany(params.get("phoneCompany"));
                company.setDescription(params.get("des"));
                comRepo.addOrUpdateCompany(company);

                employer.setId(user.getId());
                employer.setCompany(company);
                emRepo.addOrUpdateEmployer(employer);
                return user;
            } else{
                return user;
            }
        }
        return user;
    }

}
