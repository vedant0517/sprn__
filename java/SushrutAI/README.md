# SushrutAI - AI-Driven Public Health Assistant

🏥 **Mission**: Provide disease awareness, preventive healthcare tips, vaccination reminders, and local-language health guidance for rural and semi-urban India.

## 🌟 Core Features

### 🗣️ Dialog Agent
- Multi-lingual conversations (Hindi, Odia, English, tribal dialects)
- Symptom checking and health guidance
- Text, voice, and SMS support

### 📅 Vaccination Tracking
- Age-based immunization schedules (UIP compliance)
- Automated reminders via SMS/IVR
- Vaccination record management

### 🚨 Health Alerts
- Seasonal disease alerts
- Outbreak detection and notifications
- Regional health advisories

### 🛡️ Privacy Compliance
- DPDP Act 2023 compliant
- Data anonymization and encryption
- Consent management

### 📱 Multi-Channel Support
- SMS for low-connectivity areas
- IVR for voice interactions
- WhatsApp integration
- Offline fallback capabilities

## 🏗️ Architecture

```
SushrutAI/
├── src/main/java/com/sushrutai/
│   ├── SushrutAIApplication.java     # Main Spring Boot application
│   ├── controller/                   # REST API controllers
│   │   └── DialogController.java     # Health conversation API
│   ├── model/                        # JPA entities
│   │   ├── User.java                 # User data (DPDP compliant)
│   │   ├── VaccinationRecord.java    # Vaccination tracking
│   │   └── SymptomReport.java        # Anonymous symptom data
│   ├── service/                      # Business logic
│   ├── repository/                   # Data access layer
│   └── config/                       # Configuration classes
├── src/main/resources/
│   └── application.properties        # App configuration
└── pom.xml                          # Maven dependencies
```

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL (for production) or H2 (for development)

### Quick Start

1. **Clone and Navigate**
   ```bash
   cd SushrutAI
   ```

2. **Configure Environment Variables**
   ```bash
   # For SMS integration (optional)
   export TWILIO_ACCOUNT_SID=your_account_sid
   export TWILIO_AUTH_TOKEN=your_auth_token
   export TWILIO_PHONE_NUMBER=+1234567890
   
   # For data encryption
   export ENCRYPTION_KEY=your_secret_key
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - Health Check: `http://localhost:8080/api/dialog/health`
   - H2 Console: `http://localhost:8080/h2-console`

## 📚 API Documentation

### Health Dialog APIs

#### Check Service Health
```bash
GET /api/dialog/health
```

#### Report Symptoms
```bash
POST /api/dialog/symptom
Content-Type: application/json

{
  "symptoms": "fever and cough",
  "language": "HINDI",
  "severity": "MODERATE"
}
```

#### Get Vaccination Schedule
```bash
GET /api/dialog/vaccination/INFANT_0_1
```

#### Emergency Contacts
```bash
GET /api/dialog/emergency
```

## 🛠️ Development

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package
java -jar target/sushrutai-health-assistant-1.0.0.jar
```

### Database Setup (Production)
1. Create MySQL database: `sushrutai_db`
2. Update `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sushrutai_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## 🌍 Supported Languages

- **Hindi**: हिंदी (Primary)
- **Odia**: ଓଡ଼ିଆ (Regional)
- **English**: For urban users
- **Tribal dialects**: Santali, Ho, Munda

## 🔒 Privacy & Compliance

### DPDP Act 2023 Compliance
- ✅ Consent management
- ✅ Data minimization
- ✅ Anonymization of health data
- ✅ Right to erasure
- ✅ Data portability
- ✅ Secure data storage

### Data Protection
- Encrypted sensitive data
- Anonymous symptom reporting
- District-level location tracking only
- Configurable data retention policies

## 📞 Communication Channels

### SMS Integration
- Uses Twilio for SMS delivery
- Fallback for low-connectivity areas
- Regional language support

### IVR System
- Voice-based interaction
- Multi-language prompts
- Accessibility for low-literacy users

### WhatsApp Business API
- Rich media support
- Interactive buttons
- Appointment reminders

## 🎯 Use Cases

1. **Symptom Checking**
   - "मुझे बुखार और खांसी है" (I have fever and cough)
   - AI provides guidance and next steps

2. **Vaccination Reminders**
   - Age-based vaccine scheduling
   - SMS/IVR notifications for due dates

3. **Health Alerts**
   - Seasonal disease warnings
   - Outbreak notifications
   - Preventive care tips

4. **Emergency Support**
   - Quick access to emergency numbers
   - Nearest healthcare facility information

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Government of India's Digital Health Mission
- WHO health guidelines
- NFHS-5 health survey data
- ASHA workers for ground-level insights

---

**🏥 SushrutAI - Democratizing Healthcare Through AI**

*"स्वास्थ्य सबका अधिकार" - Health is Everyone's Right*