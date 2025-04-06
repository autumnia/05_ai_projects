# RAG

## PDF 다운로드 주소
- https://spri.kr/posts?code=AI-Brief


## docker
```dockerfile
docker run -d \
  --name pgvector \
  -e POSTGRES_DB=mydatabase \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_USER=myuser \
  -p 5432:5432 \
  pgvector/pgvector:pg16
  
docker run -d --name pgvector -h pgvector -p 5432:5432 -e POSTGRES_DB=mydatabase -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=myuser pgvector/pgvector:pg16  
```

## defendencies
```gradle
    implementation 'org.springframework.ai:spring-ai-pdf-document-reader'
```
