import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';

const Team_detail = (props) => {
	//console.log(props.location.pathname);
	console.log(props.match.params.id);
	//const teamId = props.location.pathname;
	const teamId = props.match.params.id;

	const [team, setTeam] = useState([]);
	const [user, setUser] = useState([]);
	
	const { id, explaintation, name } = team;
	//console.log("\==========owner "+owner);
	//console.log(owner);
	//const {nickname} = owner;
	//const nickname = team.owner.nickname;
	//const nickname = owner.nickname;
	//console.log(nickname);
	//const nickname = owner.getAttribute("nickname");
	// const nickname = owner.getNamedItem("nickname").value;
	//console.log(owner);

	useEffect(() => {
		// 해당 페이지에 진입했을 때 한 번만 실행
		fetch(`http://localhost:8000/teamDetail/${teamId}`, {
			method: "get",
		}) // 위 주소에서 데이터를 받아와서
			.then((res) => {
				return res.json();
			}) // 받아온 데이터를 json type으로 바꿔서
			.then((res) => {
				setTeam(res);
				console.log(res);
				console.log("team detail ===============");
				console.log(res.owner.id);
				setUser(res.owner);
				console.log(user.id);
			}); // state 변수에 넣어준다
	}, []);

	const joinTeamReq = () => {
		//alert("신청");
		fetch(`http://localhost:8000/user/apply1/${teamId}`, {
			method: "post",
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}

		}) // 위 주소에서 데이터를 받아와서
			.then((res) => {
				console.log(res);
				return res.text();
			}).then(res => {
				console.log(res);
				/*if (res.text = "ok") {
					return "팀가입에  성공하였습니다.";

				} else {
					return "팀 가입에  실패하였습니다.";
				}*/

			});
		};

	return (
		<div>
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
		</div>
	);
};

export default Team_detail;