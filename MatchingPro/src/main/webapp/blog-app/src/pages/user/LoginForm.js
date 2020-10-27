import React, { useEffect, useState } from 'react';
import { Container, Form, Col, Button } from 'react-bootstrap';

function LoginForm() {

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
      loginid : user.loginid,
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
          data = data.substring(7);
          console.log(data);
          localStorage.setItem("Authorization", data);
        }
      }
      return res.text();
    }).then(res => {
      //console.log("두 번째 then의 res", res);
      alert(res);   // 로그인의 결과
    });
  }

  useEffect(() => {
  }, []);

  return (
    <Container>
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

      <Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>아이디</Form.Label>
						<Form.Control
							type="text"
							name="loginid"
							placeholder="아이디"
							onChange={inputHandle}
							value={user.loginid} />
				
					</Form.Group>





					<Form.Group as={Col} controlId="formGridPassword">
						<Form.Label>비밀번호</Form.Label>
						<Form.Control
							type="password"
							name="password"
							placeholder="비밀번호"
							onChange={inputHandle}
							value={user.password} />
					</Form.Group>
          <Button variant="success" onClick={loginRequest}>로그인</Button>{' '}
    </Container>
  );
}

export default LoginForm;
