import React, { useState } from 'react';
import { Container } from 'react-bootstrap';


const JoinForm = () => {
	const joinRequest = () => {
		let person = {
			username: user.username,
			password: user.password
		}

		fetch("http://localhost:8000/joinProc", {
			method: "POST",
			body: JSON.stringify(person),
			headers: {
				'Content-Type': "application/json; charset=utf-8"
			}
		}).then(res => {
			return res.text();
		}).then(res => {
			alert(res);   // 로그인의 결과
		});
	}


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
			<hr />
			<button onClick={joinRequest}>join</button>
			<br />
			<hr />
		</Container>
	);
};

export default JoinForm;