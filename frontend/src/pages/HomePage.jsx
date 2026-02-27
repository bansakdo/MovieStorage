import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';

export default function HomePage() {
  const [keyword, setKeyword] = useState('');
  const navigate = useNavigate();

  const handleSearch = (e) => {
    e.preventDefault();
    if (keyword.trim()) {
      navigate(`/search?keyword=${encodeURIComponent(keyword.trim())}`);
    }
  };

  return (
    <header className="masthead bg-primary text-white py-5">
      <Container className="position-relative">
        <Row className="justify-content-center">
          <Col xl={6} className="text-center">
            <h1 className="mb-5">
              수백개의 영화, TV 프로그램, <br />배우들을 검색해 보세요!
            </h1>
            <Form onSubmit={handleSearch}>
              <Row>
                <Col>
                  <Form.Control
                    type="text"
                    size="lg"
                    placeholder="영화, TV 프로그램, 인물 검색.."
                    value={keyword}
                    onChange={(e) => setKeyword(e.target.value)}
                  />
                </Col>
                <Col xs="auto">
                  <Button variant="light" size="lg" type="submit">검색</Button>
                </Col>
              </Row>
            </Form>
          </Col>
        </Row>
      </Container>
    </header>
  );
}

