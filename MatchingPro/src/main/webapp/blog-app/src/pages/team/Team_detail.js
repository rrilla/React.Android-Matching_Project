import React, { useState, useEffect } from 'react';
import { Button, Container } from 'react-bootstrap';

const Team_detail = (props) => {
	console.log("Team_detail:: 해당 page의 팀 ID: ", props.match.params.id);

	const teamId = props.match.params.id;
	const [team, setTeam] = useState([]);
	const [user, setUser] = useState([]);	// team 정보 안에 user 객체를 따로 저장  
	
	const { id, explaintation, name } = team;

	useEffect(() => {
		fetch(`http://localhost:8000/teamDetail/${teamId}`, {
			method: "get",
		}).then((res) => res.json())
			.then((res) => {
				setTeam(res);
				console.log("Team_detail:: fetch 받아온 team의 response", res);
				setUser(res.owner);
			});
	}, []);

	const joinTeamReq = () => {
		console.log("눌러짐");
		fetch(`http://localhost:8000/user/apply1/${teamId}`, {
			method: "post",
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") alert("팀가입 요청 완료");
				else alert("팀가입 요청 실패");
			});
		};

	return (
		<Container>
			팀 이름 : {name}
			<br />
			팀 이름 : {id}
			<br />
			팀 이름 : {explaintation}
			<br />
			팀장번호: {user.id}
			<br />
			팀장닉네임: {user.nickname}
			<br />
			<Button onClick={joinTeamReq} variant="outline-success">가입신청</Button>
		</Container>
	);
};

export default Team_detail;