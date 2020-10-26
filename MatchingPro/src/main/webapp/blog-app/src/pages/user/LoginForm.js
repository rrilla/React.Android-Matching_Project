import React, { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';

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

  const deleteUser = (e) => {
    e.preventDefault();
    localStorage.removeItem("Authorization");
  };

  const submitUser = (e) => {
    e.preventDefault();
    console.log(user);
    let jwtToken = localStorage.getItem("Authorization");
    if (jwtToken != null) {
      alert("token이 정상적으로 localStorage에 등록 되었습니다");
    }else{
      alert("현재 토큰 없음");
    }
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

  const detailRequest = () => {
    fetch("http://localhost:8000/person/1", {
      method: "GET",
      headers: {
        'Authorization': localStorage.getItem("Authorization")
      }
    }).then(res => {
      console.log("detail fetch의 첫 번째 then의 res: " + res);
      console.log(res);
      return res.json();
    }).then(res => {
      console.log(res);
      setUser(res);
      //alert(res);   // 로그인의 결과
    });
  }

  useEffect(() => {
  }, []);

  return (
    <Container>
      <input
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
      <br />
    </Container>
  );
}

export default LoginForm;
