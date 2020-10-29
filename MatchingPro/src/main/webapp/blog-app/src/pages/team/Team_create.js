import React, { useState } from 'react';
import { Container } from 'react-bootstrap';

const Team_create = () => {
	const [team, setTeam] = useState({
		name: "",
		explaintation: ""
	});


	const joinRequest = () => {
		let team2 = {
			name: team.name,
			explaintation: team.explaintation
		}
		console.log(team2);
		fetch("http://localhost:8000/user/create", {
			method: "POST",
			body: JSON.stringify(team2),
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then(res => {
			return res.text();
		}).then(res => {
			alert(res);   // 로그인의 결과
		});
	}



	const inputHandle = (e) => {
		setTeam({
			...team,
			[e.target.name]: e.target.value,
		});
	};

	return (
		<Container>
			<input
				type="text"
				name="name"
				placeholder="name"
				onChange={inputHandle}
				value={team.name}
			/>
			<br />
			<input
				type="text"
				name="explaintation"
				placeholder="explaintation"
				onChange={inputHandle}
				value={team.explaintation}
			/>
			<br />
			<button onClick={joinRequest}>join</button>

		</Container>
	);
};

export default Team_create;