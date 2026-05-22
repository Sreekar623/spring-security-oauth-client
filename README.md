# OAuth2 Authentication & Resource Server System

A hybrid authentication and authorization system built using Spring Boot and Spring Security, combining OAuth2, JWT authentication, and session-based security workflows.

This project demonstrates secure authentication architecture using an OAuth2 Authorization Server, Resource Server, and a Spring Security Client application handling user registration, password reset, session management, and secured API communication.

---

# Project Modules

- OAuth2 Authorization Server
- OAuth2 Resource Server
- Spring Security Client Application

---

# Features

## OAuth2 Authorization Server

- OAuth2 Authentication
- JWT Token Generation
- Access Token Issuing
- Client Authentication
- Secure Authorization Flow

## Resource Server

- Protected REST APIs
- JWT Token Validation
- Secure Endpoint Protection
- Token-Based Authorization
- Spring Security Integration

## Spring Security Client

- Session-Based Authentication
- User Registration
- Password Reset
- Change Password Functionality
- OAuth2 Client Integration
- Secure API Communication
- Hybrid Authentication Workflow

---

# Hybrid Authentication Architecture

This project combines:

- OAuth2 + JWT-based authentication
- Session-based authentication features

The client application handles:

- Registration
- Login Sessions
- Password Management

while secure API access is managed through:

- OAuth2 Authorization Server
- JWT Token Validation
- Resource Server Protection

---

# Tech Stack

- Java
- Spring Boot
- Spring Security
- OAuth2
- JWT (JSON Web Token)
- Spring Authorization Server
- Spring Security OAuth2 Client
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

# System Architecture

```text
Client Application
        ↓
OAuth2 Authorization Server
        ↓
JWT Access Token Generated
        ↓
Client Stores Session & Token
        ↓
Resource Server Validates Token
        ↓
Protected API Access Granted
