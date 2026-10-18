<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Portfolio de Mohamed Bocar Diagana, développeur Java/Spring Boot. Découvrez DemoPressing, une application de gestion de pressing.">
  <title>Mohamed Bocar Diagana | Développeur Java & Spring Boot</title>
  <style>
    :root {
      --bg: #0b1020; --panel: #121a2e; --panel2: #17223a; --text: #eef3ff;
      --muted: #a9b6d0; --accent: #61e6c2; --accent2: #7da7ff; --line: rgba(255,255,255,.1);
      --max: 1120px;
    }
    * { box-sizing: border-box; }
    html { scroll-behavior: smooth; }
    body { margin: 0; font-family: Inter, ui-sans-serif, system-ui, -apple-system, Segoe UI, sans-serif; background: var(--bg); color: var(--text); line-height: 1.65; }
    a { color: inherit; text-decoration: none; }
    .container { width: min(var(--max), calc(100% - 40px)); margin: auto; }
    .nav { position: sticky; top: 0; z-index: 10; background: rgba(11,16,32,.88); backdrop-filter: blur(16px); border-bottom: 1px solid var(--line); }
    .nav-inner { display: flex; align-items: center; justify-content: space-between; min-height: 76px; gap: 20px; }
    .brand { font-weight: 850; letter-spacing: -.04em; font-size: 1.1rem; }
    .brand span { color: var(--accent); }
    .nav-links { display: flex; align-items: center; gap: 24px; color: var(--muted); font-size: .92rem; }
    .nav-links a:hover { color: var(--accent); }
    .menu-btn { display: none; border: 1px solid var(--line); background: var(--panel); color: var(--text); border-radius: 10px; padding: 8px 12px; }
    .hero { padding: 92px 0 76px; overflow: hidden; position: relative; }
    .hero:before { content: ""; position: absolute; width: 500px; height: 500px; right: -140px; top: -130px; background: radial-gradient(circle, rgba(97,230,194,.16), transparent 68%); pointer-events: none; }
    .hero-grid { display: grid; grid-template-columns: 1.35fr .8fr; gap: 55px; align-items: center; }
    .eyebrow { color: var(--accent); text-transform: uppercase; letter-spacing: .16em; font-size: .76rem; font-weight: 800; }
    h1 { font-size: clamp(2.7rem, 6vw, 5rem); line-height: 1.04; letter-spacing: -.065em; margin: 18px 0 22px; }
    h1 span { color: var(--accent); }
    .lead { max-width: 650px; color: var(--muted); font-size: 1.12rem; }
    .actions { display: flex; flex-wrap: wrap; gap: 13px; margin-top: 30px; }
    .btn { display: inline-flex; justify-content: center; align-items: center; gap: 9px; padding: 12px 18px; border-radius: 12px; font-weight: 750; border: 1px solid var(--line); transition: transform .2s, background .2s; }
    .btn:hover { transform: translateY(-2px); }
    .btn-primary { background: var(--accent); color: #061b19; border-color: transparent; }
    .btn-secondary { background: rgba(255,255,255,.03); }
    .profile-card { position: relative; border: 1px solid var(--line); background: linear-gradient(145deg, rgba(23,34,58,.96), rgba(18,26,46,.94)); border-radius: 28px; padding: 27px; box-shadow: 0 25px 80px rgba(0,0,0,.22); }
    .avatar { width: 74px; height: 74px; border-radius: 22px; display: grid; place-items: center; font-size: 1.55rem; font-weight: 900; color: #071b19; background: linear-gradient(135deg, var(--accent), #a6f5df); }
    .profile-card h2 { margin: 18px 0 4px; font-size: 1.35rem; }
    .profile-card p { color: var(--muted); margin: 0; }
    .status { display: inline-flex; align-items: center; gap: 8px; font-size: .82rem; color: #bdf9e9; background: rgba(97,230,194,.09); border: 1px solid rgba(97,230,194,.2); padding: 7px 10px; border-radius: 99px; margin-top: 20px; }
    .dot { width: 7px; height: 7px; background: var(--accent); border-radius: 50%; }
    .mini-stats { display: grid; grid-template-columns: repeat(2,1fr); gap: 12px; margin-top: 24px; }
    .mini-stat { padding: 13px; background: rgba(255,255,255,.035); border-radius: 13px; }
    .mini-stat strong { display: block; color: var(--accent); font-size: 1.1rem; }
    .mini-stat small { color: var(--muted); }
    section { padding: 76px 0; }
    .section-head { max-width: 690px; margin-bottom: 32px; }
    .section-head h2 { font-size: clamp(2rem, 4vw, 3rem); letter-spacing: -.05em; line-height: 1.15; margin: 9px 0 12px; }
    .section-head p { color: var(--muted); margin: 0; }
    .about-grid { display: grid; grid-template-columns: 1.2fr .8fr; gap: 24px; }
    .panel { border: 1px solid var(--line); background: var(--panel); border-radius: 20px; padding: 25px; }
    .panel p { color: var(--muted); }
    .highlight { color: var(--accent); font-weight: 750; }
    .skills-grid { display: grid; grid-template-columns: repeat(3,1fr); gap: 16px; }
    .skill-card { border: 1px solid var(--line); background: var(--panel); border-radius: 18px; padding: 22px; }
    .skill-icon { width: 42px; height: 42px; display: grid; place-items: center; border-radius: 12px; background: rgba(97,230,194,.1); color: var(--accent); font-weight: 900; margin-bottom: 15px; }
    .skill-card h3 { margin: 0 0 7px; }
    .skill-card p { color: var(--muted); font-size: .93rem; margin: 0; }
    .project { display: grid; grid-template-columns: 1fr 1fr; gap: 28px; align-items: stretch; }
    .project-visual { min-height: 360px; border-radius: 22px; border: 1px solid var(--line); padding: 18px; background: linear-gradient(145deg, #172b42, #101629); display: flex; flex-direction: column; justify-content: center; }
    .mock-window { border: 1px solid rgba(255,255,255,.13); border-radius: 14px; background: #0c1323; padding: 14px; box-shadow: 0 20px 55px rgba(0,0,0,.25); }
    .mock-top { display: flex; gap: 5px; padding-bottom: 14px; border-bottom: 1px solid var(--line); }
    .mock-top i { width: 7px; height: 7px; border-radius: 50%; background: #66748f; }
    .mock-title { font-size: .8rem; color: var(--muted); margin: 14px 0; }
    .mock-cards { display: grid; grid-template-columns: repeat(3,1fr); gap: 8px; }
    .mock-card { border: 1px solid var(--line); background: #151f34; border-radius: 8px; padding: 10px 8px; }
    .mock-card span { display: block; color: var(--muted); font-size: .61rem; }
    .mock-card strong { color: var(--accent); font-size: 1.15rem; }
    .mock-row { margin-top: 8px; height: 12px; border-radius: 5px; background: linear-gradient(90deg, #283a56 65%, #19243a 65%); }
    .mock-row:nth-child(odd) { background: linear-gradient(90deg, #283a56 45%, #19243a 45%); }
    .project-copy h3 { font-size: 1.7rem; margin: 0 0 12px; letter-spacing: -.035em; }
    .project-copy p { color: var(--muted); }
    .tags { display: flex; flex-wrap: wrap; gap: 8px; margin: 20px 0; }
    .tag { border: 1px solid rgba(97,230,194,.18); background: rgba(97,230,194,.07); color: #c4f8e9; padding: 6px 10px; border-radius: 8px; font-size: .8rem; }
    .feature-list { list-style: none; padding: 0; margin: 18px 0 24px; }
    .feature-list li { color: var(--muted); margin: 9px 0; }
    .feature-list li:before { content: "✓"; color: var(--accent); font-weight: 900; margin-right: 10px; }
    .steps { display: grid; grid-template-columns: repeat(4,1fr); gap: 14px; }
    .step { padding: 20px; border-left: 2px solid var(--accent); background: var(--panel); border-radius: 0 14px 14px 0; }
    .step b { color: var(--accent); font-size: .8rem; letter-spacing: .08em; }
    .step h3 { font-size: 1rem; margin: 8px 0; }
    .step p { font-size: .88rem; color: var(--muted); margin: 0; }
    .contact-box { display: flex; justify-content: space-between; align-items: center; gap: 25px; padding: 32px; border: 1px solid rgba(97,230,194,.23); border-radius: 22px; background: linear-gradient(110deg, rgba(97,230,194,.09), rgba(125,167,255,.06)); }
    .contact-box h2 { font-size: clamp(1.7rem, 4vw, 2.5rem); letter-spacing: -.05em; margin: 0 0 8px; }
    .contact-box p { color: var(--muted); margin: 0; }
    footer { border-top: 1px solid var(--line); padding: 25px 0; color: var(--muted); font-size: .88rem; }
    .footer-inner { display: flex; justify-content: space-between; flex-wrap: wrap; gap: 12px; }
    @media (max-width: 800px) {
      .hero-grid, .about-grid, .project { grid-template-columns: 1fr; }
      .hero { padding-top: 65px; }
      .skills-grid { grid-template-columns: repeat(2,1fr); }
      .steps { grid-template-columns: repeat(2,1fr); }
      .nav-links { display: none; position: absolute; top: 76px; left: 0; right: 0; padding: 18px 20px; background: #0b1020; border-bottom: 1px solid var(--line); flex-direction: column; align-items: flex-start; }
      .nav-links.open { display: flex; }
      .menu-btn { display: inline-block; }
      .contact-box { align-items: flex-start; flex-direction: column; }
    }
    @media (max-width: 520px) {
      .container { width: min(100% - 28px, var(--max)); }
      .skills-grid, .steps { grid-template-columns: 1fr; }
      .hero { padding: 55px 0; }
      section { padding: 58px 0; }
      .profile-card, .panel { padding: 20px; }
    }
  </style>
</head>
<body>
  <header class="nav">
    <div class="container nav-inner">
      <a class="brand" href="#accueil">MB<span>.</span></a>
      <button class="menu-btn" id="menuBtn" aria-label="Ouvrir le menu" aria-expanded="false">Menu ☰</button>
      <nav class="nav-links" id="navLinks" aria-label="Navigation principale">
        <a href="#apropos">À propos</a><a href="#competences">Compétences</a><a href="#projet">Projet</a><a href="#parcours">Méthode</a><a href="#contact">Contact</a>
      </nav>
    </div>
  </header>

  <main>
    <section class="hero" id="accueil">
      <div class="container hero-grid">
        <div>
          <div class="eyebrow">Développeur informatique · Java / Spring Boot</div>
          <h1>Je transforme les idées en <span>applications utiles.</span></h1>
          <p class="lead">Je suis Mohamed Bocar Diagana, ingénieur en informatique avec 3 ans d’expérience en maintenance applicative (TMA), correction d’anomalies et évolution de fonctionnalités. Je conçois des solutions fiables, structurées et orientées vers les besoins métier.</p>
          <div class="actions">
            <a class="btn btn-primary" href="#projet">Découvrir DemoPressing ↘</a>
            <a class="btn btn-secondary" href="#contact">Me contacter</a>
          </div>
        </div>
        <aside class="profile-card">
          <div class="avatar">MB</div>
          <h2>Mohamed Bocar Diagana</h2>
          <p>Ingénieur en informatique · Développeur Java</p>
          <div class="status"><span class="dot"></span> Ouvert aux opportunités professionnelles</div>
          <div class="mini-stats">
            <div class="mini-stat"><strong>3 ans</strong><small>Expérience professionnelle</small></div>
            <div class="mini-stat"><strong>Java</strong><small>Développement backend</small></div>
            <div class="mini-stat"><strong>Spring</strong><small>API & applications web</small></div>
            <div class="mini-stat"><strong>TMA</strong><small>Maintenance évolutive</small></div>
          </div>
        </aside>
      </div>
    </section>

    <section id="apropos">
      <div class="container">
        <div class="section-head">
          <div class="eyebrow">À propos</div>
          <h2>Un développeur qui aime comprendre, résoudre et améliorer.</h2>
          <p>Mon approche associe analyse du besoin, code maintenable et amélioration continue.</p>
        </div>
        <div class="about-grid">
          <div class="panel">
            <h3>Mon profil</h3>
            <p>En tant que développeur TMA, je travaille sur l’analyse des anomalies, la correction des bugs, la maintenance des applications existantes et l’ajout de nouvelles fonctionnalités. Je m’intéresse particulièrement au développement backend Java, aux API REST et à la création d’applications de gestion.</p>
            <p>Avec <span class="highlight">DemoPressing</span>, je mets en pratique ces compétences dans un cas concret : la gestion des clients, des commandes de pressing, des articles à laver et du suivi des paiements.</p>
          </div>
          <div class="panel">
            <h3>Ma façon de travailler</h3>
            <ul class="feature-list">
              <li>Comprendre le besoin fonctionnel</li>
              <li>Analyser et résoudre les problèmes</li>
              <li>Écrire un code clair et maintenable</li>
              <li>Tester les fonctionnalités et les API</li>
              <li>Faire évoluer l’application avec méthode</li>
            </ul>
          </div>
        </div>
      </div>
    </section>

    <section id="competences">
      <div class="container">
        <div class="section-head">
          <div class="eyebrow">Compétences techniques</div>
          <h2>Les outils que j’utilise pour développer.</h2>
          <p>Une base technique orientée applications web, services backend et données.</p>
        </div>
        <div class="skills-grid">
          <article class="skill-card"><div class="skill-icon">J</div><h3>Java & Java EE</h3><p>Programmation orientée objet, logique métier et développement d’applications.</p></article>
          <article class="skill-card"><div class="skill-icon">S</div><h3>Spring Boot</h3><p>Création d’applications web, contrôleurs, services et API REST.</p></article>
          <article class="skill-card"><div class="skill-icon">DB</div><h3>Bases de données</h3><p>SQL, PostgreSQL, MySQL, modélisation et accès aux données avec JPA/Hibernate.</p></article>
          <article class="skill-card"><div class="skill-icon">API</div><h3>Tests d’API</h3><p>Vérification des endpoints avec Postman et outils de documentation REST.</p></article>
          <article class="skill-card"><div class="skill-icon">UI</div><h3>Web & interface</h3><p>HTML, CSS, Bootstrap et templates Thymeleaf pour des interfaces adaptatives.</p></article>
          <article class="skill-card"><div class="skill-icon">OPS</div><h3>Outils & qualité</h3><p>Git, Maven, Docker et démarche de maintenance, diagnostic et correction.</p></article>
        </div>
      </div>
    </section>

    <section id="projet">
      <div class="container">
        <div class="section-head">
          <div class="eyebrow">Projet principal</div>
          <h2>DemoPressing — gestion d’un pressing.</h2>
          <p>Un projet métier conçu pour organiser le suivi des vêtements et articles confiés au pressing, de leur dépôt jusqu’à leur livraison.</p>
        </div>
        <div class="project">
          <div class="project-visual" aria-label="Illustration stylisée d'un tableau de bord de gestion">
            <div class="mock-window">
              <div class="mock-top"><i></i><i></i><i></i></div>
              <div class="mock-title">DEMO PRESSING / Tableau de bord</div>
              <div class="mock-cards">
                <div class="mock-card"><span>Clients</span><strong>CRM</strong></div>
                <div class="mock-card"><span>Commandes</span><strong>CMD</strong></div>
                <div class="mock-card"><span>Paiements</span><strong>FCFA</strong></div>
              </div>
              <div class="mock-title">Suivi des articles et commandes</div>
              <div class="mock-row"></div><div class="mock-row"></div><div class="mock-row"></div><div class="mock-row"></div>
            </div>
          </div>
          <div class="project-copy">
            <h3>Du dépôt de l’article au suivi du paiement</h3>
            <p>DemoPressing est une application de gestion de pressing qui aide à centraliser les informations importantes et à suivre le traitement des articles. Le projet comprend notamment une gestion des clients, des commandes, des détails de commande, des paiements et des factures.</p>
            <div class="tags"><span class="tag">Java 17</span><span class="tag">Spring Boot</span><span class="tag">Spring Security</span><span class="tag">Thymeleaf</span><span class="tag">Bootstrap</span><span class="tag">PostgreSQL</span><span class="tag">JPA / Hibernate</span></div>
            <ul class="feature-list">
              <li>Gestion des clients et de leurs coordonnées</li>
              <li>Enregistrement des commandes et des articles à traiter</li>
              <li>Suivi des étapes de traitement et de livraison</li>
              <li>Suivi des avances, paiements et montants restants</li>
              <li>Gestion des factures et génération de documents PDF</li>
              <li>Authentification et accès selon les rôles utilisateurs</li>
            </ul>
            <p><strong>Valeur ajoutée :</strong> réduire la dispersion des informations et faciliter le suivi quotidien des opérations du pressing.</p>
            <a class="btn btn-primary" href="#contact">Parlons du projet ↗</a>
          </div>
        </div>
        <p style="font-size:.86rem;color:var(--muted);margin-top:18px">Note : l’illustration ci-dessus est une maquette visuelle, pas une capture d’écran réelle de l’application.</p>
      </div>
    </section>

    <section id="parcours">
      <div class="container">
        <div class="section-head">
          <div class="eyebrow">Ma méthode</div>
          <h2>Du besoin à une fonctionnalité testée.</h2>
          <p>Une démarche structurée pour maintenir et faire évoluer une application.</p>
        </div>
        <div class="steps">
          <article class="step"><b>ÉTAPE 01</b><h3>Analyser</h3><p>Comprendre le besoin, reproduire l’anomalie et identifier sa cause.</p></article>
          <article class="step"><b>ÉTAPE 02</b><h3>Concevoir</h3><p>Définir la solution et vérifier les impacts sur les données et les services.</p></article>
          <article class="step"><b>ÉTAPE 03</b><h3>Développer</h3><p>Implémenter la correction ou la nouvelle fonctionnalité proprement.</p></article>
          <article class="step"><b>ÉTAPE 04</b><h3>Tester</h3><p>Valider le résultat et vérifier que les fonctions existantes restent stables.</p></article>
        </div>
      </div>
    </section>

    <section id="contact">
      <div class="container">
        <div class="contact-box">
          <div><div class="eyebrow">Contact</div><h2>Construisons quelque chose d’utile.</h2><p>Vous recrutez un développeur Java/Spring Boot ou souhaitez échanger sur DemoPressing ?</p></div>
          <div class="actions" style="margin:0">
            <a class="btn btn-primary" href="mailto:VOTRE_EMAIL@example.com?subject=Contact%20portfolio%20-%20Mohamed%20Bocar">M’écrire par e-mail ↗</a>
            <a class="btn btn-secondary" href="#accueil">Retour en haut ↑</a>
          </div>
        </div>
        <p style="color:var(--muted);font-size:.88rem;margin-top:14px">Avant de publier, remplacez l’adresse e-mail de démonstration par votre adresse professionnelle et ajoutez vos liens LinkedIn/GitHub.</p>
      </div>
    </section>
  </main>

  <footer><div class="container footer-inner"><span>© <span id="year"></span> Mohamed Bocar Diagana</span><span>Développement Java · Spring Boot · Applications web</span></div></footer>
  <script>
    const menuBtn = document.getElementById('menuBtn');
    const navLinks = document.getElementById('navLinks');
    menuBtn.addEventListener('click', () => {
      const opened = navLinks.classList.toggle('open');
      menuBtn.setAttribute('aria-expanded', String(opened));
    });
    navLinks.querySelectorAll('a').forEach(link => link.addEventListener('click', () => {
      navLinks.classList.remove('open');
      menuBtn.setAttribute('aria-expanded', 'false');
    }));
    document.getElementById('year').textContent = new Date().getFullYear();
  </script>
</body>
</html>
