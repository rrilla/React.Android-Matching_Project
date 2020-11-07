import React from 'react';
import styled from 'styled-components';

const Style = styled.h3`
    color : black;
`;

const TitleH3TagStyle = (props) => {
	return (
		<Style>
			{props.msg}
		</Style>
	);
};

export default TitleH3TagStyle;