import React from "react";
import { Link } from "react-router-dom";

const Header = () => {

  return (

    <nav class="navbar navbar-expand-md bg-success navbar-dark">
      {/* <!-- Brand --> */}
      <a class="navbar-brand" href="#">Navbar</a>

      {/* <!-- Toggler/collapsibe Button --> */}
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      {/* <!-- Navbar links --> */}
      <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
          <li class="nav-item">
            <Link to="/Login">Login</Link>      </li>
          <li class="nav-item">
            <Link to="/Join">Join</Link>
          </li>
          <li class="nav-item">
            <Link to="/Team_create">create team</Link>
          </li>
        </ul>
      </div>
    </nav>
  )
};

export default Header;