import React, {  } from "react";
import { Link } from "react-router-dom";
import { Form, Button, Navbar, Nav, NavDropdown, FormControl } from 'react-bootstrap';
import styled from "styled-components";

const Header = (props) => {

  const isToken = props.data;
  const setIsToken = props.data2;
  //const [isToken, setIsToken] = useState(0);  

/*   const setToken = () =>{
    if(isToken) setIsToken(0);
    else setIsToken(1);
  }  */

  const tokenCheck = () => {

    //let isToken = false;

    console.log(localStorage.getItem("Authorization"));
    //alert("as");
    //return true;  // 토큰 있으면

  } 

  // const Narae = <div></div>;

  // if(isToken) {
  //   Narae = <Nav.Link><Link to="/Login/1"><LinkStyle>로그인</LinkStyle></Link></Nav.Link>;
  // }

  //---------이게 하윤이가 해놓은 거였음--------
  // const narae = (flag) =>{
  //   if(flag) return          <Nav.Link><Link to="/Logout"><LinkStyle>Logout</LinkStyle></Link></Nav.Link>;
  //   else return <Nav.Link><Link to="/Login/1"><LinkStyle>로그인</LinkStyle></Link></Nav.Link>;
  // }
//---------이게 하윤이가 해놓은 거였음--------

  const narae = (flag) =>{
    if(flag) return          <Nav.Link><Link to="/Logout"><LinkStyle>Logout</LinkStyle></Link></Nav.Link>;
    else return <Nav.Link><Link to="/Login/1"><LinkStyle>로그인</LinkStyle></Link></Nav.Link>;
  }
  const LinkStyle = styled.span`
    color : black;
  `;

  return (
    <Navbar bg="success" expand="lg">
      token:{isToken}
      <Navbar.Brand > <Nav.Link><Link to="/"><LinkStyle>main lofo</LinkStyle></Link></Nav.Link></Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          {tokenCheck()}

            {/* isToken 0이면 로그인x -> 그러면 로그인이랑 회원가입 버튼을 보여준다 */}
            
           {/* <Nav.Link><Link to="/Login/1"><LinkStyle>로그인</LinkStyle></Link></Nav.Link>
           <Nav.Link><Link to="/Join"><LinkStyle>회원가입</LinkStyle></Link></Nav.Link> */}

              {narae(isToken)}

            {/* isToken 1이면 로그인o -> 마이페이지, 로그아웃을 보여준다 */}
          <NavDropdown title="마이페이지" id="basic-nav-dropdown">
            <NavDropdown.Item href="#action/3.1">내 프로필</NavDropdown.Item>
            <NavDropdown.Item href="#action/3.2">경기일정</NavDropdown.Item>
            <NavDropdown.Item ><Nav.Link><Link to="/MyTeam"><LinkStyle>우리팀 보기</LinkStyle></Link></Nav.Link></NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item href="#action/3.4">주변 경기 보기</NavDropdown.Item>
            {/* <NavDropdown.Item><Link to="/Logout">Logout</Link></NavDropdown.Item> */}
          </NavDropdown>
        </Nav>
        <Form inline>
          <FormControl type="text" placeholder="Search" className="mr-sm-2" />
          <Button variant="outline-success">Search</Button>
        </Form>
      </Navbar.Collapse>
    </Navbar>
    // <nav class="navbar navbar-expand-md bg-success navbar-dark">
    //   {/* <!-- Brand --> */}
    //   <a class="navbar-brand" href="#">Navbar</a>

    //   {/* <!-- Toggler/collapsibe Button --> */}
    //   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    //     <span class="navbar-toggler-icon"></span>
    //   </button>

    //   {/* <!-- Navbar links --> */}
    //   <div class="collapse navbar-collapse" id="collapsibleNavbar">
    //     <ul class="navbar-nav">
    //       <li class="nav-item">
    //         <Link to="/Login">Login</Link>      </li>
    //          <li class="nav-item">
    //         <Link to="/Logout">Logout</Link>      </li>
    //       <li class="nav-item">
    //         <Link to="/Join">Join</Link>
    //       </li>
    //       <li class="nav-item">
    //         <Link to="/Team_create">create team</Link>
    //       </li>
    //     </ul>
    //   </div>
    // </nav>
  )
};

export default Header;