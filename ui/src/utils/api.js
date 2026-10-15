import axios from 'axios';

export const postData = async (url = '', data = {}) => {
    return await axios.post(url, data)
        .then(response => response.data)
        .catch(e => {
            console.error("Error in postData:", e);
            throw e;
        });
}

export const getData = async (url = '') => {
    return await axios.get(url, {
        headers: {
            'Content-Type': 'application/json'
        },
        })
        .then(response => {
            return response.data
        })
        .catch(e => {
            console.error("Error in getData:", e);
            throw e;
        })
}

export const getPrintableDate = (date) => {
    const formatter = new Intl.DateTimeFormat('no-NO', {
        day: '2-digit',
        month: 'long',
        year: '2-digit'
    });
    const parts = Object.fromEntries(
        formatter.formatToParts(date).filter(p => p.type !== 'literal').map(p => [p.type, p.value])
    )
    return `${parts.day} ${parts.month} ${parts.year}`

}