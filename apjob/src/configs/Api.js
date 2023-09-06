import axios from "axios";

const SERVER_CONTEXT = "/apjob_javaBTL";

export const endpoints = {

}

export default axios.create({
    baseURL: "http://localhost:8085"
})