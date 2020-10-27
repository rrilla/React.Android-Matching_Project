import React, { useState } from 'react';
import { Container, Form, Button, Col } from 'react-bootstrap';
import styled from "styled-components";

const Input_style = styled.input`
		 width: 30%;
 		 padding: 15px;
 		 margin: 5px 0 22px 0;
 		 display: inline-block;
 		 border: none;
	 	 background: #f1f1f1;
		 align : center;
	`;

const JoinForm = () => {



	// 	const Input_container = styled.div`
	// 		 display: -ms-flexbox; /* IE10 */
	//  		 display: flex;
	//  		 width: 100%;
	//  		 margin-bottom: 15px;
	// 	`;

	// 	const icon = styled.div`
	// 		  padding: 10px;
	//   background: dodgerblue;
	//   color: white;
	//   min-width: 50px;
	//   text-align: center;
	// 	`;

	let emptyFlag = true;	// 빈 칸 확인 플레그 true : 가입가능
	let idCheckFlag = false; // id 중복확인 플레그 true : 사용가능
	let nicknameCheckFlag = false;
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

		const keys = Object.keys(person) // ['name', 'weight', 'price', 'isFresh']
		

		for (let i = 0; i < keys.length; i++) {
			const key = keys[i] // 각각의 키
			const value = person[key] // 각각의 키에 해당하는 각각의 값

			if (value == "") {
				emptyFlag = false;	// 빈 값 들어오면 가입 불가능
			}
		}


		if (emptyFlag & idCheckFlag & nicknameCheckFlag) {
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
		} else {
			if(!emptyFlag){	// emptyflag가 f면 alert띄우는거지 
				alert("빈 값 있음");
			}
			if(!idCheckFlag){
				alert("id중복확인을 해 주세요");
			}
			if(!nicknameCheckFlag){
				alert("nickname 중복확인을 해 주세요");
			}
		}
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


	const idDuplicateCheck = () => {
		fetch(`http://localhost:8000/idCheck/${user.loginid}`, {
			method: "GET",
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res == "ok") {
					idCheckFlag=true;
					alert("사용 가능한 아이디  입니다");
				} else {
					alert("중복 아이디 입니다");
				}
			});
	}


	const nicknameDuplicateCheck = () => {

		fetch(`http://localhost:8000/nicknameCheck/${user.nickname}`, {
			method: "GET",
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res == "ok") {
					nicknameCheckFlag=true;
					alert("사용 가능한 닉네임  입니다");
				} else {
					alert("중복  닉네임  입니다");
				}
			});
	}


	return (
		<Container>
			<Input_style
				type="text"
				name="loginid"
				placeholder="아이디"
				onChange={inputHandle}
				value={user.loginid}

			/>
			<button onClick={idDuplicateCheck}>아이디 중복검사 </button>
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
			<button onClick={nicknameDuplicateCheck}>닉네임 중복검사</button>
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


		/*<Container>
				<Form>
			<Form.Group as={Col} controlId="formGridEmail">
			  <Form.Label>ID</Form.Label>
			  <Form.Control 
				  type="text" 
				  name="loginid" 
				  placeholder="Enter ID" 
				  onChange={inputHandle} 
				  value={user.loginid}/>
			</Form.Group>
		
			<Form.Group as={Col} controlId="formGridEmail">
			  <Form.Label>PW</Form.Label>
			  <Form.Control 
				  type="password" 
				  name="password" 
				  placeholder="Enter PW" 
				  onChange={inputHandle} 
				  value={user.password}/>
			</Form.Group>
		
			<Form.Group as={Col} controlId="formGridPassword">
			  <Form.Label>Password</Form.Label>
			  <Form.Control type="password" placeholder="Password" />
			</Form.Group>
		
		  <Form.Group controlId="formGridAddress1">
			<Form.Label>Address</Form.Label>
			<Form.Control placeholder="1234 Main St" />
		  </Form.Group>
		
		  <Form.Group controlId="formGridAddress2">
			<Form.Label>Address 2</Form.Label>
			<Form.Control placeholder="Apartment, studio, or floor" />
		  </Form.Group>
		
		  <Form.Row>
			<Form.Group as={Col} controlId="formGridCity">
			  <Form.Label>City</Form.Label>
			  <Form.Control />
			</Form.Group>
		
			<Form.Group as={Col} controlId="formGridState">
			  <Form.Label>State</Form.Label>
			  <Form.Control as="select" defaultValue="Choose...">
				<option>Choose...</option>
				<option>...</option>
			  </Form.Control>
			</Form.Group>
		
			<Form.Group as={Col} controlId="formGridZip">
			  <Form.Label>Zip</Form.Label>
			  <Form.Control />
			</Form.Group>
		  </Form.Row>
		
		  <Form.Group id="formGridCheckbox">
			<Form.Check type="checkbox" label="Check me out" />
		  </Form.Group>
		
		  <Button variant="primary" type="submit">
			Submit
		  </Button>
		</Form>
		</Container>*/
	);
};

export default JoinForm;