import React, { useEffect, useState } from 'react';
import { Jumbotron, Row, Container, Col, Button } from 'react-bootstrap';
import styled from 'styled-components';

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;

const Team_schedule = (props) => {
	const id = props.match.params.id;
	const [schedule, setSchedule] = useState([]);
	const [teaminfo, setTeaminfo] = useState([]);

	const zzz = () =>{
		console.log(schedule);
	} 
	useEffect(() => {
		fetch(`http://localhost:8000/battleList/${id}`, {
			method: "get",
		}).then((res) => {
			console.log("team schedule fetch fisrt then res :: ", res);
			return res.json();
		}).then((res) => {
			console.log("team schedule fetch second then res", res);
			setSchedule(res);
		});

		fetch(`http://localhost:8000/teamDetail/${id}`, {
			method: "get",
		}).then((res) => {
			return res.json();
		}).then((res) => {
			setTeaminfo(res);
		});
	}, []);

	return (
		<Container>
			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						{teaminfo.name}팀 경기일정
						<Row>
						 	{schedule.map((res) => (
								<div>요청팀 = {res.requestTeam.name} vs 수락팀 = {res.responseTeam.name} //////////</div> 
							))}
						</Row>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container>
	);
};

export default Team_schedule;