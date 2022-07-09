import React, {Component} from 'react';
import Footer from './Footer'
import { Container, Nav, Navbar, Offcanvas } from 'react-bootstrap';
import { NavLink } from "react-router-dom";

class CustomNavbar extends Component {

    render() {
        return (
	    <>
		{/* fixed="top" */}
		<Navbar expand={false}>
		    <Container fluid>
			<NavLink to="/">React-Bootstrap</NavLink>
			<Navbar.Toggle aria-controls="offcanvasNavbar" />
			<Navbar.Offcanvas id="offcanvasNavbar"
					  aria-labelledby="offcanvasNavbarLabel"
					  placement="end">
			    <Offcanvas.Header closeButton>  
				<Offcanvas.Title id="offcanvasNavbarLabel">Sidebar
				</Offcanvas.Title>  
			    </Offcanvas.Header>
			    <Offcanvas.Body>
				<Nav className="justify-content-end flex-grow-1 pe-3">  
				    <NavLink to="/profile">Profile</NavLink>  
				    <NavLink to="/about">About</NavLink>
				</Nav>
	    			<Footer />
			    </Offcanvas.Body>
			</Navbar.Offcanvas>
		    </Container>
		</Navbar>
	    </>
        );
    }

}
export default CustomNavbar;
