# MailEase - Smart Email Generator

## 🚀 Overview
MailEase is an AI-powered smart email generator designed to streamline email composition. It leverages Google's **Gemini API** to generate high-quality, context-aware emails efficiently. The project consists of a **Spring Boot** backend, a **React.js** frontend, and a **Google Chrome extension** for seamless integration with Gmail.

## 🎯 Features
- ✨ **AI-Powered Email Generation**: Uses Gemini API to craft personalized emails.
- 📩 **Gmail Integration**: Chrome extension for direct email composition inside Gmail.
- ⚡ **Real-Time Suggestions**: Provides instant email recommendations based on user input.
- 🎨 **User-Friendly Interface**: Intuitive design for effortless navigation and usage.
- 🔐 **Secure & Scalable**: Built with Spring Boot and React for robust performance.

## 🛠️ Tech Stack
- **Backend**: Spring Boot, Gemini API
- **Frontend**: React.js, Tailwind CSS
- **Browser Extension**: Chrome Extension API

## 📌 Installation
### Backend (Spring Boot)
```bash
# Clone the repository
git clone https://github.com/LakshayJain458/mailease.git
cd mailease/backend

# Configure application.properties (DB, API keys)

# Build and run the application
mvn spring-boot:run
```

### Frontend (React.js)
```bash
cd ../frontend
npm install
npm start
```

### Chrome Extension
1. Navigate to `chrome://extensions/`.
2. Enable **Developer Mode** (top right corner).
3. Click **Load Unpacked** and select the `extension` folder.
4. Open Gmail and start using MailEase!

## 📖 Usage Guide
1. Open the MailEase web app or use the Chrome extension inside Gmail.
2. Enter context or key points for your email.
3. Let the AI generate a draft.
4. Edit and send with ease!

## 📜 License
This project is licensed under the **MIT License**.

