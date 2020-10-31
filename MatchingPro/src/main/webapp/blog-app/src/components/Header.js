import React, { } from "react";
import { Link } from "react-router-dom";
import { Form, Button, Navbar, Nav, NavDropdown, FormControl, Row } from 'react-bootstrap';
import styled from "styled-components";

const LinkStyle = styled.span`
    color : black;
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
        <Nav.Link><Link to="/Logout"><LinkStyle>Logout</LinkStyle></Link></Nav.Link>
        <NavDropdown title="MYPAGE" id="basic-nav-dropdown">
            <NavDropdown.Item ><Nav.Link><Link to="/"><LinkStyle>MyPage(x)</LinkStyle></Link></Nav.Link></NavDropdown.Item>
            <NavDropdown.Item ><Nav.Link><Link to="/MyTeam"><LinkStyle>MyTeam(o)</LinkStyle></Link></Nav.Link></NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item ><Nav.Link><Link to="/"><LinkStyle>MyTeam(x)</LinkStyle></Link></Nav.Link></NavDropdown.Item>
          </NavDropdown>
        </Row>
    }
    else {
      return <Row>
        <Nav.Link><Link to="/Login"><LinkStyle>로그인</LinkStyle></Link></Nav.Link>
        <Nav.Link><Link to="/Join"><LinkStyle>회원가입</LinkStyle></Link></Nav.Link>
        </Row>
    }
  }

  return (
    <Navbar bg="success" expand="lg">
      <Navbar.Brand ><Nav.Link><Link to="/"><LinkStyle>main lofo</LinkStyle></Link></Nav.Link></Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          {isLogin(isToken)}
        </Nav>
        <Form inline>
          <FormControl type="text" placeholder="Search" className="mr-sm-2" />
          <Button variant="outline-success"><LinkStyle>Search</LinkStyle></Button>
        </Form>
      </Navbar.Collapse>
    </Navbar>
  )
};

export default Header;