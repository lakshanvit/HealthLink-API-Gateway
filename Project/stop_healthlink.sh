// 5. Create stop script - stop_healthlink.sh
#!/bin/bash

echo "Stopping HealthLink Microservices..."

if [ -f "healthlink.pids" ]; then
    PIDS=$(cat healthlink.pids)
    IFS=',' read -ra PID_ARRAY <<< "$PIDS"

    for pid in "${PID_ARRAY[@]}"; do
        if [ ! -z "$pid" ]; then
            echo "Stopping process $pid"
            kill $pid 2>/dev/null
        fi
    done

    rm healthlink.pids
    echo "All services stopped!"
else
    echo "No PID file found. Manually kill Java processes if needed."
fi