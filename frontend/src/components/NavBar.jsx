import { Navbar, Container, Button, Nav } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import api from '../api/client';

export default function NavBar({ user, setUser }) {
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await api.post('/auth/logout');
      setUser(null);
      navigate('/');
    } catch (e) {
      console.error('로그아웃 실패', e);
    }
  };

  return (
    <Navbar bg="light" expand="lg" className="static-top">
      <Container>
        <Navbar.Brand as={Link} to="/">Movie Storage</Navbar.Brand>
        <Navbar.Toggle aria-controls="main-nav" />
        <Navbar.Collapse id="main-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/videos">영상 목록</Nav.Link>
            <Nav.Link as={Link} to="/users">사용자 목록</Nav.Link>
          </Nav>
          <div>
            {user ? (
              <>
                <span className="me-3">{user.username}님</span>
                <Button variant="outline-primary" size="sm" onClick={handleLogout}>로그아웃</Button>
              </>
            ) : (
              <Button as={Link} to="/login" variant="primary" size="sm">로그인</Button>
            )}
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}


