import { Link } from 'react-router-dom';
import './Header.css'

const Header = () => {
    return (
        <div className='header fixed-top'>
            <div className="container">
                <div className="row">  
                    <div className='col-8'></div>                
                    <Link className='col-1' to="/signup">
                        <button >Sign up</button>
                    </Link>

                    <Link className='col-1' to="/login">
                        <button>Sign in</button>
                    </Link>

                    <Link className='col-2' to="/">
                        <button onPress={()=>console.log("upload")} id="Upload jobs">Upload jobs</button>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default Header;