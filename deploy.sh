#!/bin/bash

# Dynamic Configuration
CURRENT_USER=$(whoami)
PROJECT_DIR=$(pwd)
SERVICE_NAME="citizen-connect"
JAR_NAME="citizen-connect-backend-0.0.1-SNAPSHOT.jar"

echo "Deploying as user: $CURRENT_USER in $PROJECT_DIR"

# Load and prepare environment variables for systemd
if [ -f .env ]; then
    grep -v '^#' .env | sed 's/[[:space:]]*=[[:space:]]*/=/g' | sed 's/^export //g' > .env.systemd
    echo "Prepared .env.systemd for systemd service"
fi

# Check for Java
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed."
    echo "Please run: sudo dnf install java-17-amazon-corretto-devel -y (Amazon Linux) or sudo apt install openjdk-17-jdk -y (Ubuntu/Pop!_OS)"
    exit 1
fi

# Ensure mvnw is executable
chmod +x mvnw

# Build the application
echo "Building the application..."
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

# Create/Update the systemd service file dynamically
cat <<EOF > $SERVICE_NAME.service
[Unit]
Description=Citizen Connect Backend Service
After=syslog.target network.target

[Service]
User=$CURRENT_USER
WorkingDirectory=$PROJECT_DIR
ExecStart=$(command -v java) -jar $PROJECT_DIR/target/$JAR_NAME
EnvironmentFile=$PROJECT_DIR/.env.systemd
SuccessExitStatus=143
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
EOF

# Install or Update the service in systemd
echo "Installing/Updating systemd service..."
sudo cp $SERVICE_NAME.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable $SERVICE_NAME

# Restart the service to apply changes
echo "Restarting service..."
sudo systemctl restart $SERVICE_NAME

echo "------------------------------------------------"
echo "Deployment Complete!"
echo "Status: $(sudo systemctl is-active $SERVICE_NAME)"
echo "Check logs with: sudo journalctl -u $SERVICE_NAME -f"
echo "------------------------------------------------"
