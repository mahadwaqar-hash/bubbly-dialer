#!/data/data/com.termux/files/usr/bin/bash
# Auto-launch BubblyDialer with 1 command
PORT=8080
APP_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$APP_DIR"

# Check if server is already running in background
if ! pgrep -f "python.*http.server.*$PORT" > /dev/null; then
    echo "⚡ Launching BubblyDialer server on port $PORT..."
    python -m http.server $PORT > /dev/null 2>&1 &
    sleep 1
else
    echo "⚡ BubblyDialer server is already active on port $PORT"
fi

# Automatically open in Android browser
if command -v termux-open-url >/dev/null 2>&1; then
    termux-open-url "http://localhost:$PORT"
elif command -v termux-open >/dev/null 2>&1; then
    termux-open "http://localhost:$PORT"
else
    am start -a android.intent.action.VIEW -d "http://localhost:$PORT" > /dev/null 2>&1
fi

echo "🚀 App opened at http://localhost:$PORT"
