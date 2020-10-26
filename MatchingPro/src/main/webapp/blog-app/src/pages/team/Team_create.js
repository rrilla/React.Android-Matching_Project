import React, { useState } from 'react';
import { Container } from 'react-bootstrap';

const Team_create = () => {
	const [team, setTeam] = useState({
		teamname: "",
	});


	const joinRequest = () => {
		let team2 = {
			teamname: team.teamname,
		}

		fetch("http://localhost:8000/joinProc2", {
			method: "POST",
			body: JSON.stringify(team2),
			headers: {
				'Content-Type': "application/json; charset=utf-8"
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
				name="teamname"
				placeholder="teamname"
				onChange={inputHandle}
				value={team.teamname}
			/>
			<br />
			<button onClick={joinRequest}>join</button>

		</Container>
	);
};

export default Team_create;