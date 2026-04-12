import React from "react";

function Navbar() {

  const logout = () => {
    localStorage.removeItem("username");
    window.location = "/login";
  };

  return (
    <div>
      <a href="/home">Home</a> |
      <a href="/profile">Profile</a> |
      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default Navbar;