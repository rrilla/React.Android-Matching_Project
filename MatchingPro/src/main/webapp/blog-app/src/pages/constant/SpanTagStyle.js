import React from 'react';
import styled from 'styled-components';

const Style = styled.span`
	color : black;
	font-weight : 700;
`;

const SpanTagStyle = (props) => {
	return (
		<Style>
			{props.msg}
		</Style>
	);
};

export default SpanTagStyle;