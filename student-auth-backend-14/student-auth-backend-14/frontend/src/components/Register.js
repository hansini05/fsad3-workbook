import React, { useState } from "react";
import axios from "axios";

function Register() {
  const [user, setUser] = useState({
    username: "",
    password: "",
    email: ""
  });

  const handleChange = (e) => {
    setUser({...user, [e.target.name]: e.target.value});
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/api/users/register", user)
      .then(() => {
        alert("Registered Successfully");
        window.location = "/login";
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Register</h2>
      <input name="username" placeholder="Username" onChange={handleChange}/>
      <input name="email" placeholder="Email" onChange={handleChange}/>
      <input name="password" type="password" placeholder="Password" onChange={handleChange}/>
      <button type="submit">Register</button>
    </form>
  );
}

export default Register;