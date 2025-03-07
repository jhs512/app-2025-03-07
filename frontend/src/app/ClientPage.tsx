"use client";

import { useEffect, useState } from "react";

export default function ClientPage() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/posts")
      .then((res) => res.json())
      .then((data) => setPosts(data));
  }, []);

  return (
    <>
      <h1>글 목록</h1>
      <ul>
        {posts.map(
          (
            post: any // eslint-disable-line @typescript-eslint/no-explicit-any
          ) => (
            <li key={post.id}>{post.title}</li>
          )
        )}
      </ul>
    </>
  );
}
