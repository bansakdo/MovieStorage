import { Container, Row, Col } from 'react-bootstrap';

export default function Footer() {
  return (
    <footer className="footer bg-light py-4 mt-auto">
      <Container>
        <Row>
          <Col lg={6} className="text-center text-lg-start my-auto">
            <ul className="list-inline mb-2">
              <li className="list-inline-item"><a href="#!">About</a></li>
              <li className="list-inline-item">⋅</li>
              <li className="list-inline-item"><a href="#!">Contact</a></li>
              <li className="list-inline-item">⋅</li>
              <li className="list-inline-item"><a href="#!">Terms of Use</a></li>
              <li className="list-inline-item">⋅</li>
              <li className="list-inline-item"><a href="#!">Privacy Policy</a></li>
            </ul>
            <p className="text-muted small mb-4 mb-lg-0">&copy; Movie Storage 2024. All Rights Reserved.</p>
          </Col>
          <Col lg={6} className="text-center text-lg-end my-auto">
            <ul className="list-inline mb-0">
              <li className="list-inline-item me-4"><a href="#!"><i className="bi-facebook fs-3"></i></a></li>
              <li className="list-inline-item me-4"><a href="#!"><i className="bi-twitter fs-3"></i></a></li>
              <li className="list-inline-item"><a href="#!"><i className="bi-instagram fs-3"></i></a></li>
            </ul>
          </Col>
        </Row>
      </Container>
    </footer>
  );
}

