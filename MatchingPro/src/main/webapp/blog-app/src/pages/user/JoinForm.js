import React, { useState } from 'react';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed, Form, FormControl, Modal } from 'react-bootstrap';
import styled from "styled-components";
import { Link } from 'react-router-dom';

/*const Input_style = styled.input`
			width: 30%;
			padding: 15px;
			margin: 5px 0 22px 0;
			display: inline-block;
			border: none;
			background: #f1f1f1;
			align : center;
		`;*/
const LinkStyle = styled.span`
  color : black;
`;
const SpanStyle = styled.span`
	line-height:100%
`;

let emptyFlag = true;				// 빈 칸 확인 플레그 true : 가입가능
let idCheckFlag = false;			// id 중복확인 플레그 true : 사용가능
let nicknameCheckFlag = false; 		// nickname 중복확인 플레그 true : 사용가능

const JoinForm = () => {

	const openTextFile = (e) => {
		e.preventDefault();
		var input = document.createElement("input");
		input.type = "file";
		input.accept = "image/*";
		input.id = "uploadInput";
		input.click();
		input.onchange = function (event) {
			processFile(event.target.files[0]);
		};
	}

	function processFile(file) {
		var reader = new FileReader();
		reader.onload = function () {
			var result = reader.result;
			console.log(result);
			setUser({
				...user,
				"image": result
			});
		};
		reader.readAsDataURL(file);
	}

	const [user, setUser] = useState({
		loginid: "",
		username: "",
		password: "",
		nickname: "",
		email: "",
		phone: "",
		location: "",
		image: ""
	});

	const inputHandle = (e) => {
		setUser({
			...user,
			[e.target.name]: e.target.value,
		});
	};

	const idDuplicateCheck = (e) => {
		e.preventDefault();
		fetch(`http://localhost:8000/idCheck/${user.loginid}`, {
			method: "GET",
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res === "ok") {
					idCheckFlag = true;
					alert("사용 가능한 아이디  입니다");
				} else {
					alert("중복 아이디 입니다");
				}
			});
	}

	const nicknameDuplicateCheck = (e) => {
		e.preventDefault();
		fetch(`http://localhost:8000/nicknameCheck/${user.nickname}`, {
			method: "GET",
		}).then(res => res.text())
			.then(res => {
				if (res === "ok") {
					nicknameCheckFlag = true;
					alert("사용 가능한 닉네임  입니다");
				} else {
					alert("중복  닉네임  입니다");
				}
			});
	}

	const joinRequest = (e) => {
		e.preventDefault();
		let person = {
			loginid: user.loginid,
			username: user.username,
			password: user.password,
			nickname: user.nickname,
			email: user.email,
			phone: user.phone,
			location: user.location,
			image: user.image
		}
		const keys = Object.keys(person) // ['name', 'weight', 'price', 'isFresh']
		for (let i = 0; i < keys.length; i++) {
			const key = keys[i] // 각각의 키
			const value = person[key] // 각각의 키에 해당하는 각각의 값

			if (value === "") {
				console.log("joinForm:: empty key: ", key);
				console.log("joinForm:: empty value: ", value);
				emptyFlag = false;	// 빈 값 들어오면 가입 불가능
			} else {
				emptyFlag = true;
			}
		}
		if (emptyFlag) {
			fetch("http://localhost:8000/join", {
				method: "POST",
				body: JSON.stringify(person),
				headers: {
					'Content-Type': "application/json; charset=utf-8"
				}
			}).then(res => {
				if (res.text = "ok") return "회원가입에 성공하였습니다.";
				else return "회원가입 실패하였습니다.";
			}).then(res => {
				alert(res);   // 로그인의 결과
			});
		} else {
			if (!emptyFlag) alert("빈 값 있음");
			if (!idCheckFlag) alert("id중복확인을 해 주세요");
			if (!nicknameCheckFlag) alert("nickname 중복확인을 해 주세요");
		}
	}

	return (
		<Container>
			<br /><br /><br /><br /><br />

			<Jumbotron>
				<Form>
					<Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>아이디</Form.Label>
						<Row>
							<Col md={10}>
								<Form.Control
									type="text"
									name="loginid"
									placeholder="아이디"
									onChange={inputHandle}
									value={user.loginid} /></Col>
							<Col md={2}><Button variant="dark" onClick={idDuplicateCheck}>아이디 중복검사</Button>{' '}
							</Col>
						</Row>
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

					<Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>이름</Form.Label>
						<Form.Control
							type="text"
							name="username"
							placeholder="이름"
							onChange={inputHandle}
							value={user.username} />
					</Form.Group>

					<Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>닉네임</Form.Label>
						<Row>
							<Col md={10}>
								<Form.Control
									type="text"
									name="nickname"
									placeholder="아이디"
									onChange={inputHandle}
									value={user.nickname} />
							</Col>
							<Col md={2}>
								<Button variant="dark" onClick={nicknameDuplicateCheck}>닉네임 중복검사</Button>{' '}
							</Col>
						</Row>
					</Form.Group>

					<Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>이메일</Form.Label>
						<Form.Control
							type="email"
							name="email"
							placeholder="이메일"
							onChange={inputHandle}
							value={user.email} />
					</Form.Group>

					<Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>휴대폰 번호 </Form.Label>
						<Form.Control
							type="tel"
							name="phone"
							placeholder="휴대폰번호"
							onChange={inputHandle}
							value={user.tel} />
					</Form.Group>

					<Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>지역 </Form.Label>
						<Form.Control
							type="text"
							name="location"
							placeholder="지역을 입력하세요"
							onChange={inputHandle}
							value={user.location} />
					</Form.Group>
					<Form.Group as={Col} controlId="formGridEmail">
						<Button variant="dark" onClick={openTextFile}>Select Image</Button>{' '}
					</Form.Group>

					<Form.Group as={Col} controlId="formGridEmail">
						<hr/>
						<Button variant="info" onClick={joinRequest}>회원가입</Button>{' '}
					</Form.Group>

				</Form>
			</Jumbotron>


		</Container>
	);
};

export default JoinForm;