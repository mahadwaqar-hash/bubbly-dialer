#!/data/data/com.termux/files/usr/bin/bash
# Push to GitHub helper script for mahadwaqar-hash/bubbly-dialer
set -e

REPO_URL="https://github.com/mahadwaqar-hash/bubbly-dialer.git"
cd "$(dirname "$0")"

echo "⚡ Initializing git and preparing files..."
git init

git config user.name "mahadwaqar-hash" || true
git config user.email "mahadwaqar@users.noreply.github.com" || true

git add .
git commit -m "Build BubblyDialer native Android dialer APK" || echo "No new changes to commit."

git branch -M main

# Set remote URL
if git remote | grep -q 'origin'; then
    git remote set-url origin "$REPO_URL"
else
    git remote add origin "$REPO_URL"
fi

echo "🚀 Ready to push to $REPO_URL"
echo "Running: git push -u origin main"
git push -u origin main
