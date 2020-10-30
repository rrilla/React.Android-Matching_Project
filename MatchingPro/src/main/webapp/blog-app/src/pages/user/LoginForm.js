import React, { useEffect, useState } from 'react';
import { Container, Form, Col, Button, Row, ListGroup, Badge } from 'react-bootstrap';
import styled from 'styled-components';



function LoginForm(props) {

  const setToken = props.setToken;
  console.log(6, setToken)
  const isToken = props.data;
  const setIsToken = props.data2;

  const [user, setUser] = useState({
    loginid: "",
    password: "",
  });



  // const [isToken, setIsToken] = useState(false);  
  // 토큰 있는 지 없는 지 확인용 state. 0면 없고  1면 있다 

  const inputHandle = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const Form_Style = styled.input`
        width: 100%;
        padding: 15px;
        margin: auto;
        display: inline-block;
        border: solid black;
        background: #f1f1f1;
        align : center;
      `;

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
      //console.log("첫 번째 then의 res", res);
      for (let header of res.headers.entries()) {


        if (header[0] == "authorization") {
          let data = header[1];
          //data = data.substring(7);
          console.log(data);
          localStorage.setItem("Authorization", data);

          console.log(props.location);

          setToken();

          //setIsToken(true);
          //console.log(isToken);
          //if(isToken){
          //  console.log("토큰 생성");
          //}else{
          //  console.log("토큰 생성 안됨");
          //}


        }
      }
      return res.text();
    }).then(res => {
      //console.log("두 번째 then의 res", res);
      alert(res);   // 로그인의 결과

      //location.isToken(true);
      //console.log(isToken);
    });
  }

  useEffect(() => {
  }, []);

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
