# ⚡ BubblyDialer • Cold-Calling & Web Design Sales App

A gamified, production-ready Cold-Calling CRM & Dialer designed for web designers and agencies selling websites to local businesses discovered on Google Maps and Google Search.

Built with **Tailwind CSS**, playful Neobrutalist design, **Web Audio API** synthesized sound effects, **Canvas Confetti**, **PWA offline support**, and **localStorage** database.

---

## 🚀 How to Upload to GitHub from Termux (Password Fix)

### Why Termux "won't let you put the password":
1. **Characters are invisible by design:** In Linux and Termux, typing a password produces **no characters or dots** on screen. It is being typed, but kept hidden for security.
2. **GitHub NO LONGER accepts account passwords:** Since August 2021, GitHub requires a **Personal Access Token (PAT)** instead of your GitHub account password.

### 🔑 3-Step Fix to Push without password prompts:

1. **Generate a GitHub Personal Access Token:**
   - In your mobile browser, go to **[github.com/settings/tokens](https://github.com/settings/tokens)**.
   - Tap **Generate new token (classic)**.
   - Note / Name: `Termux`
   - Check the **`repo`** scope checkbox (Full control of private repositories).
   - Tap **Generate token** at the bottom and **copy** the token (starts with `ghp_...`).

2. **Run this in Termux to save your token permanently:**
   ```bash
   cd ~/cold-caller-app
   git remote set-url origin https://<YOUR_TOKEN_HERE>@github.com/mahadwaqar-hash/bubbly-dialer.git
   ```
   *(Replace `<YOUR_TOKEN_HERE>` with your actual token starting with `ghp_...`)*

3. **Stage, commit, and push:**
   ```bash
   git add .
   git commit -m "Add GitHub Actions APK builder and custom Android config"
   git push -u origin main
   ```
   *It will push immediately without asking for a password!*

---

## 📦 How to Get Your Android APK (Automatic Cloud Build)

You **do not need to compile Gradle inside Termux** (which can crash on mobile due to memory limits).

A **GitHub Actions CI/CD Workflow** (`.github/workflows/build-apk.yml`) is included in this repository.

### How to download your `.apk`:
1. Push your repository to GitHub using the steps above.
2. Open your repository on GitHub in your browser:  
   `https://github.com/mahadwaqar-hash/bubbly-dialer`
3. Tap the **Actions** tab at the top.
4. Tap the latest workflow run: **`Build Android APK`**.
5. Once complete (green checkmark, ~2 minutes), scroll down to the **Artifacts** section at the bottom.
6. Tap **`BubblyDialer-Debug-APK`** to download the ZIP file.
7. Unzip the file on your phone and tap **`app-debug.apk`** to install!

---

## 📲 Instant PWA Alternative (No Build Required)

If you want to use the app immediately on your phone right now:
1. In Termux, run:
   ```bash
   cd ~/cold-caller-app && python -m http.server 8080
   ```
2. Open Chrome on your phone and go to:
   ```text
   http://localhost:8080
   ```
3. Tap Chrome's menu **(⋮)** ➔ Tap **"Install app"** (or **"Add to Home screen"**).
4. BubblyDialer will launch fullscreen as an independent standalone app with its own icon!

---

## 🎯 Key App Features

1. **Clean Production Pipeline:**
   - Starts with a clean, empty database.
   - Leads, portfolio URLs, and settings are saved securely in `localStorage`.
   - Real streak tracking starting at 0 calls today, incrementing with each dial.

2. **Smart Google Maps Link & Places Importer:**
   - Tap **"Share" ➔ "Copy link"** on any Google Maps business listing.
   - Paste into the CRM importer to automatically extract the business name and phone number.
   - Supports Google Places API keys for live ratings and review counts.

3. **Golden Lead Detection:**
   - Automatically detects if the business has no website and triggers the **🚨 NO WEBSITE DETECTED!** alert badge.

4. **Dual-Action Calling:**
   - **Cellular Call:** Launches the native Android dialer (`tel:<number>`) and opens the sales cheatsheet.
   - **WhatsApp Direct:** Opens `https://wa.me/<number>` without saving the contact, pre-filled with your chosen pitch template.

5. **In-Call Sales Cheatsheet Drawer:**
   - 15-second opening hook to bypass gatekeepers.
   - The risk-free "Free Homepage Mockup" close.
   - 4 objection destroyers (*"We just use Instagram"*, *"No budget"*, *"Send an email"*, *"We have a web guy"*).

6. **Deal Won Celebrations:**
   - Multi-cannon confetti shower (`canvas-confetti`) whenever you close a lead!
