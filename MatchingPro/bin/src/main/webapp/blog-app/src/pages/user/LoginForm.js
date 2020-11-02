import React, { useEffect, useState } from 'react';
import { Container, Form, Col, Button, Row, ListGroup, Badge } from 'react-bootstrap';
import styled from 'styled-components';

/*const Form_Style = styled.input`
        width: 100%;
        padding: 15px;
        margin: auto;
        display: inline-block;
        border: solid black;
        background: #f1f1f1;
        align : center;
      `;*/

function LoginForm(props) {

  const setToken = props.setToken;

  const [user, setUser] = useState({
    loginid: "",
    password: "",
  });

  const inputHandle = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const loginRequest = () => {
    let person = {
      loginid: user.loginid,
      password: user.password
    }

    fetch("http://localhost:8000/login", {
      method: "POST",
      body: JSON.stringify(person),
      headers: {
        'Content-Type': "application/json; charset=utf-8"
      }
    }).then(res => {
      for (let header of res.headers.entries()) {
        if (header[0] === "authorization") {
          let data = header[1];
          //data = data.substring(7);
          console.log("LoginForm:: in loginRequestdlsplay 생성된 authorization: ", data);
          localStorage.setItem("Authorization", data);
          setToken();
        }
      }
      return res.text();
    }).then(res => {
      alert(res);
    });
  }

  return (
    <div>
      <br />
      <br />
      <br />
      <br />
      <Row>
        <Col md={2}></Col>
        <Col md={8}>
          <ListGroup >
            <ListGroup.Item variant="success"> <Row><Col md={4}></Col><Col md={4}><h2>로그인</h2></Col></Row></ListGroup.Item>
            <ListGroup.Item variant="info">
              <Container>
                <br />
                <br />
                <br />
                {/* <input
          type="text"
          name="loginid"
          placeholder="loginid"
          onChange={inputHandle}
          value={user.loginid}
        />
        <br />
        <input
          type="text"
          name="password"
          placeholder="password"
          onChange={inputHandle}
          value={user.password}
        />
        <br />

        <br />
        <hr />
        <button onClick={loginRequest}>login</button>
        <br /> */}

                <Form.Row>
                  <Col md={2}></Col>
                  <Form.Group as={Col} md={8} controlId="formGridEmail">

                    <Form.Label>아이디</Form.Label>
                    <Form.Control
                      type="text"
                      name="loginid"
                      placeholder="아이디"
                      onChange={inputHandle}
                      value={user.loginid} />

                  </Form.Group>
                </Form.Row>
                {/* 
  token:{isToken} */}

                <Form.Row>
                  <Col md={2}></Col>
                  <Form.Group as={Col} md={8} controlId="formGridPassword">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control
                      type="password"
                      name="password"
                      placeholder="비밀번호"
                      onChange={inputHandle}
                      value={user.password} />
                  </Form.Group>
                </Form.Row>
                <br />
                <Form.Row>
                  <Col md={4}></Col>
                  <Button variant="success" onClick={loginRequest}>로그인</Button>{' '}


                </Form.Row>

              </Container>
              <br />

              <br />


            </ListGroup.Item>

          </ListGroup>
        </Col>
      </Row>
    </div>
  );
}

export default LoginForm;
