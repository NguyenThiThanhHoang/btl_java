import './Footer.css';
import logo from './Logo.png';

const Footer = () => {
    return (
        <div className='footer bg-dark fixed-bottom' >
            <div className='container'>
                <div className='row'>
                    <div className='col-7'>
                        <div className='row'>
                            <img className='col-2' style={{ width: 65, height: 60, paddingRight: 0, paddingLeft: 5}} src={logo} alt='logo'/>
                            <h1 className='navbar-brand col-10' style={{paddingLeft: 0}}>
                                <h1 className='text' style={{paddingLeft: 5}}>AP Jobs</h1>
                            </h1>
                        </div>
                        <p>Nguyen Thi Thanh Hoang - 1951052061</p>
                        <p>Nguyen Linh Nhi - 1951052149</p>
                        <div className='footer-icons'>
                            <i className="fa-brands fa-square-facebook"></i>&nbsp;
                            <i className="fa-brands fa-twitter"></i>&nbsp;
                            <i className="fa-brands fa-linkedin-in"></i>
                        </div>
                    </div>

                    <div className='contact col-5'>
                        <h5>Contact info</h5>
                        <ul>
                            <p className='info'>
                                <i className="fa-solid fa-phone"></i>
                                &nbsp;(+84) 0123456789
                            </p>
                            <p className='info'>
                                <i className="fa-regular fa-envelope"></i>
                                &nbsp;ap123@ap.com
                            </p>
                            <p className='info'>
                                <i className="fa-regular fa-address-book"></i>
                                &nbsp;371 Nguyễn Kiệm, Phường 3, Gò Vấp, Thành phố Hồ Chí Minh
                            </p>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Footer;