import React from "react";

function Home() {
  const username = localStorage.getItem("username");

  if(!username){
    window.location = "/login";
  }

  return (
    <div>
      <h2>Welcome {username}</h2>
    </div>
  );
}

export default Home;