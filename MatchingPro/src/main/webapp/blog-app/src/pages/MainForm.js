import React from 'react';
import Image from 'react-bootstrap/Image'
import styled from 'styled-components';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed } from 'react-bootstrap';

const MainForm = () => {

	const ImageBoxStyle = styled.div`
		width: 100%;
		margin: auto;
	
  `;
  
  
	return (
		<Container>
      <Row>

    <Breadcrumb>
  <Breadcrumb.Item href="#">Home</Breadcrumb.Item>
  <Breadcrumb.Item href="https://getbootstrap.com/docs/4.0/components/breadcrumb/">
    Library
  </Breadcrumb.Item>
  <Breadcrumb.Item active>Data</Breadcrumb.Item>
</Breadcrumb>
  </Row>

		 <Row>
   
    <Col md={11}>
   <Carousel>
  <Carousel.Item>
    
    <img
      className="d-block w-100"
      src="1slideepic.png"
      alt="First slide"
    />

    
    <Carousel.Caption>
      <h3>First slide label</h3>
      <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
    </Carousel.Caption>
  </Carousel.Item>
  <Carousel.Item>
    <img
      className="d-block w-100"
      src="2slidecool.jpg"
      alt="Third slide"
    />

    <Carousel.Caption>
      <h3>Second slide label</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
    </Carousel.Caption>
  </Carousel.Item>
  <Carousel.Item>
    <img
      className="d-block w-100"
      src="3slideground.jpg"
      alt="Third slide"
    />

    <Carousel.Caption>
      <h3>Third slide label</h3>
      <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
    </Carousel.Caption>
  </Carousel.Item>
</Carousel>
</Col>
   
   
  </Row>
<br>
</br>
  <Row>
   <Jumbotron>
  <h1>Hello, world!</h1>
  <p>
    여기는 축구를 하고 싶은 사람을 위한 플랫폼 입니다. 
    여기서 경기를 찾고, 팀을 구성하고, 축구를 즐겨보세요. 
  </p>
  <p>
    <Button variant="primary">더 자세히 알아보기 </Button>
  </p>
</Jumbotron>

  </Row>
  
  <Row>
    <Col md={4}>
<Card style={{ width: '18rem' }}>
  <Card.Img variant="top" src="1slideepic.png" />
  <Card.Body>
    <Card.Title>팀이름</Card.Title>
    <Card.Text>
     당감동 뜨란채 아파트 주민들이 모인 팀입니다.
    </Card.Text>
  </Card.Body>
  <ListGroup className="list-group-flush">
    <ListGroupItem>팀장: 이하윤</ListGroupItem>
    <ListGroupItem>승점 10전 10승</ListGroupItem>
    <ListGroupItem>최근경기 : 10/5</ListGroupItem>
  </ListGroup>
  <Card.Body>
    <Card.Link href="#">자세히보기</Card.Link>
    <Card.Link href="#">일정보기</Card.Link>
  </Card.Body>
</Card>
</Col>

<Col md={4}>
<Card style={{ width: '18rem' }}>
  <Card.Img variant="top" src="1slideepic.png" />
  <Card.Body>
    <Card.Title>팀이름</Card.Title>
    <Card.Text>
     당감동 뜨란채 아파트 주민들이 모인 팀입니다.
    </Card.Text>
  </Card.Body>
  <ListGroup className="list-group-flush">
    <ListGroupItem>팀장: 이하윤</ListGroupItem>
    <ListGroupItem>승점 10전 10승</ListGroupItem>
    <ListGroupItem>최근경기 : 10/5</ListGroupItem>
  </ListGroup>
  <Card.Body>
    <Card.Link href="#">자세히보기</Card.Link>
    <Card.Link href="#">일정보기</Card.Link>
  </Card.Body>
</Card>
</Col>

<Col md={4}>
<Card style={{ width: '18rem' }}>
  <Card.Img variant="top" src="1slideepic.png" />
  <Card.Body>
    <Card.Title>팀이름</Card.Title>
    <Card.Text>
     당감동 뜨란채 아파트 주민들이 모인 팀입니다.
    </Card.Text>
  </Card.Body>
  <ListGroup className="list-group-flush">
    <ListGroupItem>팀장: 이하윤</ListGroupItem>
    <ListGroupItem>승점 10전 10승</ListGroupItem>
    <ListGroupItem>최근경기 : 10/5</ListGroupItem>
  </ListGroup>
  <Card.Body>
    <Card.Link href="#">자세히보기</Card.Link>
    <Card.Link href="#">일정보기</Card.Link>
  </Card.Body>
</Card>
</Col>



  </Row>

  
   
  </Container>
		
		
	);
};

export default MainForm;