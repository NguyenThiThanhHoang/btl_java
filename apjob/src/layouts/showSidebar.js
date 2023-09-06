import React, {useEffect, useState} from "react";
import { useLocation } from "react-router-dom";

const ShowBar = ({children}) => {
    const location = useLocation();

    const [showBar, setShowBar] = useState(false)

    useEffect(() => {
        console.log("location", location)
        if(location.pathname === '/login' || location.pathname === '/signup') {
            setShowBar(false)
        } 
        else setShowBar(true)
    }, [location])

    return (
        <div>{showBar && children}</div>
    )
}

export default ShowBar;