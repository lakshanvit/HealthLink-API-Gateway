// 4. Create startup script - start_healthlink.sh
#!/bin/bash
echo "Starting HealthLink Microservices..."

# Start Config Server first
echo "Starting Config Server..."
cd configserver && ./mvnw spring-boot:run > ../logs/configserver.log 2>&1 &
CONFIG_PID=$!
sleep 10

# Start Eureka Server
echo "Starting Eureka Server..."
cd ../eurekaserver && ./mvnw spring-boot:run > ../logs/eurekaserver.log 2>&1 &
EUREKA_PID=$!
sleep 15

# Start API Gateway
echo "Starting API Gateway..."
cd ../api-gateway && ./mvnw spring-boot:run > ../logs/api-gateway.log 2>&1 &
GATEWAY_PID=$!
sleep 10

# Start Patient Service
echo "Starting Patient Service..."
cd ../patient && ./mvnw spring-boot:run > ../logs/patient.log 2>&1 &
PATIENT_PID=$!
sleep 10

# Start Encounter Service
echo "Starting Encounter Service..."
cd ../encounter && ./mvnw spring-boot:run > ../logs/encounter.log 2>&1 &
ENCOUNTER_PID=$!
sleep 10

# Start Observation Service
echo "Starting Observation Service..."
cd ../observation && ./mvnw spring-boot:run > ../logs/observation.log 2>&1 &
OBSERVATION_PID=$!

echo "All services started!"
echo "Config Server PID: $CONFIG_PID"
echo "Eureka Server PID: $EUREKA_PID"
echo "API Gateway PID: $GATEWAY_PID"
echo "Patient Service PID: $PATIENT_PID"
echo "Encounter Service PID: $ENCOUNTER_PID"
echo "Observation Service PID: $OBSERVATION_PID"

echo "Service URLs:"
echo "Eureka Dashboard: http://localhost:8070"
echo "API Gateway: http://localhost:8072"
echo "Config Server: http://localhost:8071"
echo "Patient Service: http://localhost:8081"
echo "Encounter Service: http://localhost:8082"
echo "Observation Service: http://localhost:8083"

# Create PID file for cleanup
echo "$CONFIG_PID,$EUREKA_PID,$GATEWAY_PID,$PATIENT_PID,$ENCOUNTER_PID,$OBSERVATION_PID" > healthlink.pids