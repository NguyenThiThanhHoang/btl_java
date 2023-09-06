import React, { useState } from "react";
import './Login.css'
import { Link } from "react-router-dom";

const Login = () => {
    const [addclass, setaddclass] = useState("");
    const [message, setMessage] = useState('');
    const [valid, setValid] = useState(false);

    const handleChange = event => {
        setMessage(event.target.value);

        if (event.target.value.trim().length > 0) {
            setValid(true)
        } 
    }

    return (
        <div className="main">
        <div className={`container ${addclass}`} id="container">
            <div className="form-container sign-up-container">
                <form>
                    <h1>Create account</h1>
                    <input onChange={handleChange} type="text" placeholder="Name" />
                    <input onChange={handleChange} type="email" placeholder="Email" />
                    <input onChange={handleChange} type="password" placeholder="Password" />
                    <Link to="/login">
                        {(valid) && (
                            <button className="btn" id="signIn" onClick={() => setaddclass("/login")}>Sign up</button>
                        )}
                        {(!valid) && (
                            <button className="btn disabled">Sign up</button>
                        )}
                    </Link>
                </form>
            </div>
            <div className="form-container sign-in-container">
                <form>
                    <h1>Sign in</h1>
                    <input type="text" placeholder="Name" />
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Password" />
                    <Link to="/">
                        <button className="btn">Sign in</button>
                    </Link>
                </form>
            </div>
            <div className="overlay-container">
                <div className="overlay">
                    <div className="overlay-panel overlay-left">
                        <button className="btn ghost" id="signIn" onClick={() => setaddclass()}> To Login</button>
                    </div>
                
                    <div className="overlay-panel overlay-right">
                        <button className="btn ghost" id="signIn" onClick={() => setaddclass("right-panel-active")} >To Register</button>
                    </div>
                </div>
            </div>
        </div>
        </div>
    );
};

export default Login;