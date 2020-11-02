import React from 'react';
import { Row, Col, Carousel } from 'react-bootstrap';
import styled from 'styled-components';

const SlideStyle = styled.div`
	margin-top:4%;
	margin-bottom:4%;
`;

const Slide = () => {
	return (
		<SlideStyle>
			<Row>
				<Col md={1}></Col>
				<Col md={10}>
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
				<Col md={1}></Col>
			</Row>
		</SlideStyle>
	);
};

export default Slide;