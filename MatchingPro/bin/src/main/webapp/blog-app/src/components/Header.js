import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Form, Button, Navbar, Nav, NavDropdown, FormControl, Row, Modal } from 'react-bootstrap';
import styled from "styled-components";
import LoginModal from "./LoginModal";
import JoinModal from "../pages/user/JoinModal";


const LinkStyle = styled.span`
    color : black;
`;

const HeaderStyle = styled.div`
  width: 100%;
  top: 0;
  position: fixed;
  z-index: 999;
`;

const Header = (props) => {
  const isToken = props.isToken;
  //const setIsToken = props.setIsToken;

  /*const tokenCheck = () => {
    console.log("header:: display Authorization", localStorage.getItem("Authorization"));
    if(localStorage.getItem("Authorization") != null) return true;
    else return false;
  }*/

  const isLogin = (flag) => {
    if (flag) {
      return <Row> 
        <NavDropdown title="MYPAGE" id="basic-nav-dropdown">
            <NavDropdown.Item ><Nav.Link><Link to="/"><LinkStyle>MyPage(x)</LinkStyle></Link></Nav.Link></NavDropdown.Item>
            <NavDropdown.Item ><Nav.Link><Link to="/MyTeam"><LinkStyle>MyTeam(o)</LinkStyle></Link></Nav.Link></NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item ><Nav.Link><Link to="/"><LinkStyle>MyTeam(x)</LinkStyle></Link></Nav.Link></NavDropdown.Item>
          </NavDropdown>
        <Nav.Link><Link to="/Logout"><LinkStyle>Logout</LinkStyle></Link></Nav.Link>
        </Row>
    }
    else {
      return <Row>
        <Nav.Link><Link to="/Login"><LoginModal setToken={props.setToken}></LoginModal></Link></Nav.Link>
        <Nav.Link><Link to="/Join"><JoinModal></JoinModal></Link></Nav.Link>
        </Row>
    }
  }

  return (
    <HeaderStyle>
      <Navbar bg="light" expand="lg">
        
      <Nav.Link><Link to="/"><LinkStyle><Navbar.Brand ><img
        src="/soccer_logo-removebg-preview.png"
        width="30"
        height="30"
        // className="d-inline-block align-top"
        alt="React Bootstrap logo"
      />{''}
      </Navbar.Brand></LinkStyle></Link></Nav.Link>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            {isLogin(isToken)}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
   
    </HeaderStyle>
  )
};

export default Header;