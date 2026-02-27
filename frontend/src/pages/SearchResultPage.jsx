import { useEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { Container, Row, Col, Form, Button, Table, Spinner } from 'react-bootstrap';
import api from '../api/client';

export default function SearchResultPage() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  const initialKeyword = searchParams.get('keyword') || '';
  const initialType = searchParams.get('searchType') || 'all';

  const [keyword, setKeyword] = useState(initialKeyword);
  const [searchType, setSearchType] = useState(initialType);
  const [videos, setVideos] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (initialKeyword) {
      fetchResults(initialKeyword, initialType);
    }
  }, [initialKeyword, initialType]);

  const fetchResults = (kw, type) => {
    setLoading(true);
    api.get('/search', { params: { keyword: kw, searchType: type } })
      .then((res) => setVideos(res.data))
      .catch((err) => console.error('검색 실패', err))
      .finally(() => setLoading(false));
  };

  const handleSearch = (e) => {
    e.preventDefault();
    if (keyword.trim()) {
      navigate(`/search?keyword=${encodeURIComponent(keyword.trim())}&searchType=${searchType}`);
      fetchResults(keyword.trim(), searchType);
    }
  };

  const mediaTypeLabel = (type) => {
    if (!type) return '-';
    return type === 'MOVIE' || type.videoType === 'MOVIE' ? '영화' : 'TV SHOW';
  };

  return (
    <Container className="mt-4">
      <h1 className="mb-4">Movie Storage Search Result</h1>

      <Form onSubmit={handleSearch} className="mb-4">
        <Row>
          <Col md={2}>
            <Form.Select value={searchType} onChange={(e) => setSearchType(e.target.value)}>
              <option value="all">전체</option>
              <optgroup label="작품">
                <option value="video">작품 전체</option>
                <option value="movie">영화</option>
                <option value="drama">TV</option>
              </optgroup>
              <option value="actor">배우</option>
            </Form.Select>
          </Col>
          <Col md={9}>
            <Form.Control
              type="text"
              placeholder="영화, TV 프로그램, 인물 검색.."
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
            />
          </Col>
          <Col md={1}>
            <Button variant="primary" type="submit" className="w-100">검색</Button>
          </Col>
        </Row>
      </Form>

      {loading ? (
        <div className="text-center mt-5"><Spinner animation="border" /></div>
      ) : (
        <Table striped bordered hover responsive>
          <thead>
            <tr>
              <th>ID</th>
              <th>제목</th>
              <th>감독</th>
              <th>한 줄 소개</th>
              <th>관람 등급</th>
              <th>평점</th>
              <th>타입</th>
              <th>배우</th>
            </tr>
          </thead>
          <tbody>
            {videos.map((video) => (
              <tr key={video.id}>
                <td>{video.id}</td>
                <td>{video.title}</td>
                <td>{video.directors}</td>
                <td>{video.summary}</td>
                <td>{video.ageRatings}</td>
                <td>{video.score}</td>
                <td>{mediaTypeLabel(video.mediaType)}</td>
                <td>
                  {video.castActors?.map((actor, idx) => (
                    <span key={actor.id}>
                      {actor.name}{idx < video.castActors.length - 1 ? ', ' : ''}
                    </span>
                  ))}
                </td>
              </tr>
            ))}
            {videos.length === 0 && (
              <tr><td colSpan={8} className="text-center">검색 결과가 없습니다.</td></tr>
            )}
          </tbody>
        </Table>
      )}
    </Container>
  );
}

