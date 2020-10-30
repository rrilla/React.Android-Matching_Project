import React from 'react';
import { ListGroup, Card, ListGroupItem } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const LinkStyle = styled.span`
    color : black;
  `;

  
const TeamCard = (props) => {


	const { id, explaintation, name, owner} = props.team;
	const nickname = owner.nickname;
	console.log("id: "+id);
	const url = "/Team_detail/"+id;
	return (
		<div>
		<Card style={{ width: '18rem' }}>
			<Card.Img variant="top" src="1slideepic.png" />
			<Card.Body>
				<Card.Title>{name}</Card.Title>
				<Card.Text>
					{explaintation}
				</Card.Text>
			</Card.Body>
			<ListGroup className="list-group-flush">
				<ListGroupItem>팀장: {nickname}</ListGroupItem>
				<ListGroupItem>승점 10전 10승</ListGroupItem>
				<ListGroupItem>최근경기 : 10/5</ListGroupItem>
			</ListGroup>
			<Card.Body>
				{/* <Card.Link href="#">자세히보기</Card.Link> */}
				<Link to={url}><LinkStyle>TeamDetail</LinkStyle></Link>
				{/* <Card.Link href="#">일정보기</Card.Link> */}
			</Card.Body>
		</Card>
		<br/>
		</div>
	);
};

export default TeamCard;