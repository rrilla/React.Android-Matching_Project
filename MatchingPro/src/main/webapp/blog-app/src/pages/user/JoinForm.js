import React, { useState } from 'react';
import { Container, Form, Button, Col, Row } from 'react-bootstrap';
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

const SpanStyle = styled.span`
	line-height:100%
`;

let emptyFlag = true;	// 빈 칸 확인 플레그 true : 가입가능
let idCheckFlag = false; // id 중복확인 플레그 true : 사용가능
let nicknameCheckFlag = false;


const JoinForm = () => {

	const openTextFile = () => {
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
			//output.innerText = result
			//document.getElementById('tartgetImg').setAttribute('src', result);
			console.log(result);
			
			setUser({
			...user,
			"image": result
		});

		};
		reader.readAsDataURL(file);
	}

	console.log("그림 새로 그려짐");

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

			if (value == "") {
				emptyFlag = false;	// 빈 값 들어오면 가입 불가능
			}
		}

		console.log("user.image"+user.image);
		console.log("person: "+person.loginid);
		console.log("person: "+person.image);
// & idCheckFlag & nicknameCheckFlag
		if (emptyFlag) {
			//fetch("http://localhost:8000/join", {
fetch("http://10.100.102.46:8000/join", {
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
			if (!emptyFlag) {	// emptyflag가 f면 alert띄우는거지 
				alert("빈 값 있음");
			}
			if (!idCheckFlag) {
				alert("id중복확인을 해 주세요");
			}
			if (!nicknameCheckFlag) {
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
				if (res == "ok") {
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
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res == "ok") {
					nicknameCheckFlag = true;
					alert("사용 가능한 닉네임  입니다");
				} else {
					alert("중복  닉네임  입니다");
				}
			});
	}


	return (



		<Container>
			<Form>
				{/* <Form.Row>

					<Form.Group as={Col} controlId="formGridEmail">
						<Form.Label>아이디</Form.Label>
						<Form.Control
							type="text"
							name="loginid"
							placeholder="아이디"
							onChange={inputHandle}
							value={user.loginid} />


					</Form.Group>
					<Form.Group as={Col} controlId="formGridEmail">
<br></br><br/>
						<Button variant="success" onClick={idDuplicateCheck}>아이디 중복검사</Button>{' '}
					</Form.Group>

				</Form.Row> */}


				<Form.Group as={Col} controlId="formGridEmail">
					<Form.Label>아이디</Form.Label>
					<Form.Control
						type="text"
						name="loginid"
						placeholder="아이디"
						onChange={inputHandle}
						value={user.loginid} />
					<SpanStyle><br /></SpanStyle>
					<Button variant="success" onClick={idDuplicateCheck}>아이디 중복검사</Button>{' '}
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
					<Form.Control
						type="text"
						name="nickname"
						placeholder="닉네임"
						onChange={inputHandle}
						value={user.nickname} />
					<SpanStyle><br /></SpanStyle>
					<Button variant="success" onClick={nicknameDuplicateCheck}>닉네임 중복검사</Button>{' '}

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

						<Form.Group id="formGridCheckbox">
							<Form.Check type="checkbox" label="이메일 수신에 동의하십니까?" />
						</Form.Group>

						{/* <Button variant="primary" type="submit">
							Submit
			</Button> */}
						<Button variant="success" onClick={joinRequest}>회원가입</Button>{' '}

			</Form>
			<button onClick={openTextFile}>Image File Open</button>
		</Container>

	);
};

export default JoinForm;