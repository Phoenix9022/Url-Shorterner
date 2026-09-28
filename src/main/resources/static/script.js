document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('url-form');
    const originalUrlInput = document.getElementById('original-url');
    const resultContainer = document.getElementById('result-container');
    const shortLinkElement = document.getElementById('short-link');
    const copyBtn = document.getElementById('copy-btn');
    const loading = document.getElementById('loading');
    const errorMessage = document.getElementById('error-message');
    const shortenBtnSpan = document.querySelector('#shorten-btn span');
    const shortenBtnIcon = document.querySelector('#shorten-btn i');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const originalUrl = originalUrlInput.value.trim();
        if (!originalUrl) return;

        // Reset state
        resultContainer.classList.add('hidden');
        errorMessage.classList.add('hidden');
        loading.classList.remove('hidden');
        shortenBtnSpan.textContent = 'Working...';
        shortenBtnIcon.className = 'fa-solid fa-spinner fa-spin';

        try {
            const response = await fetch('/api/shorten', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ originalUrl })
            });

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.error || 'Failed to shorten URL');
            }

            // Construct full short URL
            const fullShortUrl = `${window.location.origin}/${data.shortLink}`;
            
            // Update UI
            shortLinkElement.href = fullShortUrl;
            shortLinkElement.textContent = fullShortUrl;
            
            loading.classList.add('hidden');
            resultContainer.classList.remove('hidden');
            
            // Clear input
            originalUrlInput.value = '';
            
        } catch (error) {
            loading.classList.add('hidden');
            errorMessage.textContent = error.message;
            errorMessage.classList.remove('hidden');
        } finally {
            shortenBtnSpan.textContent = 'Shorten';
            shortenBtnIcon.className = 'fa-solid fa-arrow-right';
        }
    });

    copyBtn.addEventListener('click', async () => {
        const textToCopy = shortLinkElement.textContent;
        try {
            await navigator.clipboard.writeText(textToCopy);
            
            // Visual feedback
            const originalIcon = copyBtn.innerHTML;
            copyBtn.innerHTML = '<i class="fa-solid fa-check" style="color: #4ade80;"></i>';
            
            setTimeout(() => {
                copyBtn.innerHTML = originalIcon;
            }, 2000);
        } catch (err) {
            console.error('Failed to copy text: ', err);
        }
    });
});
