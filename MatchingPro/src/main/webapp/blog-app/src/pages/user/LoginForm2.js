import React, { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';

function LoginForm() {
  const [user, setUser] = useState({
    username: "",
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
      username: user.username,
      password: user.password
    }

    fetch("http://localhost:8000/loginProc", {
      method: "POST",
      body: JSON.stringify(person),
      headers: {
        'Content-Type': "application/json; charset=utf-8"
      }
    }).then(res => {
      //console.log("첫 번째 then의 res", res);
      for (let header of res.headers.entries()) {
        if (header[0] === "authorization") {
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
        name="username"
        placeholder="username"
        onChange={inputHandle}
        value={user.username}
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
      <input
        type="text"
        name="id"
        placeholder="id"
        onChange={inputHandle}
        value={user.id}
      />
      <br />
      <hr />
      <button onClick={deleteUser}>토큰삭제</button>
      <button onClick={submitUser}>확인</button>
      <br />
      <button onClick={loginRequest}>login</button>
      <br />
      <button onClick={detailRequest}>detail</button>
      <hr />
      설명<br/>
      1. 토큰 삭제 : 현재 등록된 토큰을 삭제한다<br/>
      2. 확인 : 토큰이 등록되었는지, 없는지 확인<br/>
      3. login : db에 저장된 id, pw를 비교해서 맞으면 ok가 뜨고 토큰이 생성된다. id는 입력할 필요 없다<br/>
      4. detail : id 1번의 user data를 select해서 fetch해온다. 성공하면 state로 관리되는 input창 3개에 data가 뜬다<br/>
      <hr/>
    </Container>
  );
}

export default LoginForm;
