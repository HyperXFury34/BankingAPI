import React, { useState } from "react";
import { createRoot } from "react-dom/client";
import axios from "axios";
const API = import.meta.env.VITE_API || "http://localhost:8080";

function App(){
  const [token,setToken]=useState("");
  const [email,setEmail]=useState("user@example.com");
  const [password,setPassword]=useState("pass");
  const [account,setAccount]=useState(null);

  const login=async()=>{
    const {data}=await axios.post(`${API}/auth/login`,{email,password});
    setToken(data.token);
  };

  const load=async(id)=>{
    const {data}=await axios.get(`${API}/accounts/${id}`,{ headers: { Authorization:`Bearer ${token}`}});
    setAccount(data);
  };

  return <div style={{maxWidth:600, margin:"24px auto"}}>
    <h1>Banking API Client</h1>
    <input value={email} onChange={e=>setEmail(e.target.value)} placeholder="email"/><br/>
    <input value={password} onChange={e=>setPassword(e.target.value)} type="password" placeholder="password"/><br/>
    <button onClick={login}>Login</button>

    <div style={{marginTop:16}}>
      <button onClick={()=>load(1)} disabled={!token}>Load Account #1</button>
      {account && <pre>{JSON.stringify(account,null,2)}</pre>}
    </div>
  </div>;
}

createRoot(document.getElementById("root")).render(<App/>);
