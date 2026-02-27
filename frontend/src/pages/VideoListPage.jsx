import { useEffect, useState } from 'react';
import { Container, Table, Spinner } from 'react-bootstrap';
import api from '../api/client';

export default function VideoListPage() {
  const [videos, setVideos] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get('/videos')
      .then((res) => setVideos(res.data))
      .catch((err) => console.error('영상 목록 조회 실패', err))
      .finally(() => setLoading(false));
  }, []);

  const mediaTypeLabel = (type) => {
    if (!type) return '-';
    return type === 'MOVIE' || type.videoType === 'MOVIE' ? '영화' : 'TV SHOW';
  };

  if (loading) {
    return (
      <Container className="text-center mt-5">
        <Spinner animation="border" />
      </Container>
    );
  }

  return (
    <Container className="mt-4">
      <h1 className="mb-4">Movie Storage Video List</h1>
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
              <td>
                {video.summary && video.summary.length >= 100
                  ? video.summary.substring(0, 97) + '...'
                  : video.summary}
              </td>
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
            <tr><td colSpan={8} className="text-center">등록된 영상이 없습니다.</td></tr>
          )}
        </tbody>
      </Table>
    </Container>
  );
}

