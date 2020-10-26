import React, { useState } from 'react';
import { Container } from 'react-bootstrap';
import styled from "styled-components";

const JoinForm = () => {

	const InputStyle = styled.input`
		 width: 30%;
 		 padding: 15px;
 		 margin: 5px 0 22px 0;
 		 display: inline-block;
 		 border: none;
		  background: #f1f1f1;
		  align : center;
	`;

	const 

	const joinRequest = () => {
		let person = {
			loginid: user.loginid,
			username: user.username,
			password: user.password,
			nickname: user.nickname,
			email: user.email,
			phone: user.phone,
			location: user.location
		}

		fetch("http://localhost:8000/join", {
			method: "POST",
			body: JSON.stringify(person),
			headers: {
				'Content-Type': "application/json; charset=utf-8"
			}
		}).then(res => {
			if (res.text = "ok") {
				return "회원가입에 성공하였습니다.";

			} else {
				return "회원가입 실패하였습니다.";
			}

		}).then(res => {
			alert(res);   // 로그인의 결과
		});
	}


	const [user, setUser] = useState({
		loginid: "",
		username: "",
		password: "",
		nickname: "",
		email: "",
		phone: "",
		location: ""
	});

	const inputHandle = (e) => {
		setUser({
			...user,
			[e.target.name]: e.target.value,
		});
	};

	return (
		<Container>
			<div class="input container">
			<InputStyle
				type="text"
				name="loginid"
				placeholder="아이디"
				onChange={inputHandle}
				value={user.loginid}
			
			/>
			 <i class="fa fa-envelope icon"></i>
			 </div>
			<br />
			<input
				type="password"
				name="password"
				placeholder="비밀번호"
				onChange={inputHandle}
				value={user.password}
			/>

			<br />
			<input
				type="text"
				name="username"
				placeholder="이름"
				onChange={inputHandle}
				value={user.username}
			/>

			<br />
			<input
				type="text"
				name="nickname"
				placeholder="닉네임"
				onChange={inputHandle}
				value={user.nickname}
			/>

			<br />
			<input
				type="email"
				name="email"
				placeholder="이메일"
				onChange={inputHandle}
				value={user.email}
			/>

			<br />
			<input
				type="tel"
				name="phone"
				placeholder="휴대폰번호"
				onChange={inputHandle}
				value={user.tel}
			/>

			<br />
			<input
				type="text"
				name="location"
				placeholder="지역"
				onChange={inputHandle}
				value={user.location}
			/>

			<br />
			<hr />
			<button onClick={joinRequest}>join</button>
			<br />
			<hr />
		</Container>
	);
};

export default JoinForm;