"use client";

import { useEffect, useState } from "react";

export default function ClientPage() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL;
    fetch(`${API_BASE_URL}/api/v1/posts`)
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
