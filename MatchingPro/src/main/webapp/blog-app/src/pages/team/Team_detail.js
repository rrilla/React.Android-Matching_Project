import React, { useState, useEffect } from 'react';
import { Container, Jumbotron, Button, Form, FormControl, Row, Col } from 'react-bootstrap';
import styled from 'styled-components';

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const Team_detail = (props) => {
	console.log("Team_detail:: 해당 page의 팀 ID: ", props.match.params.id);
	const teamId = props.match.params.id;
	const [team, setTeam] = useState([]);
	const { id, explaintation, name } = team;
	const [owner, setOwner] = useState([]);
	const [users, setUsers] = useState([]);

	useEffect(() => {
		fetch(`http://localhost:8000/teamDetail/${teamId}`, {
			method: "get",
		}).then((res) => res.json())
			.then((res) => {
				console.log("Team_detail:: fetch 받아온 team의 response", res);
				setTeam(res);
				setOwner(res.owner);
				setUsers(res.users);
			});
	}, []);

	const joinTeamReq = () => {
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

	const sss = () => {
		alert("대전신청 완료");
		/* fetch(`http://localhost:8000/user/apply1/${teamId}`, {
			method: "post",
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") alert("팀가입 요청 완료");
				else alert("팀가입 요청 실패");
			}); */
	};

	return (
		<Container>
			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						<Row>
							<Col md={12}><h1>⚽ {name}</h1></Col>
							<Col md={12}><hr /></Col>
							<Col md={3}><h5>👑 {owner.nickname}</h5></Col>
							<Col md={8}><h5>📄 {explaintation}</h5></Col>
							<Col md={12}><hr /></Col>
							<Col md={3}><h3>🏃‍♀️ Member</h3></Col>
							<Col md={8}></Col>
							<Col md={12}><br /></Col>
							{users.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
								<Col md={3}>🏃 {res.nickname}</Col>
							))}
							<Col md={12}><hr /></Col>
							<Col md={3}>
								<Button onClick={joinTeamReq} variant="outline-success">가입신청</Button>
							</Col>
							<Col md={3}>
								<Button onClick={sss} variant="outline-success">대전신청</Button>
							</Col>
						</Row>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container>
	);
};

export default Team_detail;