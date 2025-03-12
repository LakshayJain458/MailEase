import './App.css';
import { useState } from 'react';
import axios from 'axios';
import {
  Container, Typography, Box, TextField, FormControl,
  InputLabel, Select, MenuItem, Button, CircularProgress, IconButton, Paper
} from '@mui/material';
import { Brightness4, Brightness7, ContentCopy } from '@mui/icons-material';
import { ThemeProvider, createTheme } from '@mui/material/styles';

function App() {
  const [emailContent, setEmailContent] = useState('');
  const [tone, setTone] = useState('');
  const [generatedEmail, setGeneratedEmail] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [darkMode, setDarkMode] = useState(false);

  const theme = createTheme({
    palette: {
      mode: darkMode ? 'dark' : 'light',
      background: {
        default: darkMode ? '#121212' : '#f5f5f5'
      }
    }
  });

  const handleSubmit = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await axios.post("http://localhost:8080/api/email/generate", {
       emailContent,
       tone 
      });
      setGeneratedEmail(typeof response.data === 'string' ? response.data : JSON.stringify(response.data));
    } catch (error) {
      setError('Failed to generate email reply. Please try again');
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Box
        sx={{
          minHeight: '100vh',
          backgroundImage: darkMode ? 'url(https://images.pexels.com/photos/696644/pexels-photo-696644.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2)' : 'url(https://images.pexels.com/photos/19670/pexels-photo.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2)',
          backgroundSize: 'cover',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center'
        }}>
        <Container maxWidth="md" sx={{ py: 4 }}>
          <Paper elevation={6} sx={{ p: 4, borderRadius: 3 }}>
            <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
              <Typography variant='h4' component="h1" gutterBottom>
                Email Reply Generator
              </Typography>
              <IconButton onClick={() => setDarkMode(!darkMode)}>
                {darkMode ? <Brightness7 /> : <Brightness4 />}
              </IconButton>
            </Box>

            <TextField 
              fullWidth
              multiline
              rows={6}
              variant='outlined'
              label="Original Email Content"
              value={emailContent || ''}
              onChange={(e) => setEmailContent(e.target.value)}
              sx={{ mb:2 }}/>

            <FormControl fullWidth sx={{ mb:2 }}>
              <InputLabel>Tone (Optional)</InputLabel>
              <Select
                value={tone || ''}
                label="Tone (Optional)"
                onChange={(e) => setTone(e.target.value)}>
                  <MenuItem value="">None</MenuItem>
                  <MenuItem value="professional">Professional</MenuItem>
                  <MenuItem value="casual">Casual</MenuItem>
                  <MenuItem value="friendly">Friendly</MenuItem>
              </Select>
            </FormControl>

            <Button
              variant='contained'
              onClick={handleSubmit}
              disabled={!emailContent || loading}
              fullWidth
              sx={{ py: 1.5, fontSize: '1rem' }}>
              {loading ? <CircularProgress size={24}/> : "Generate Reply"}
            </Button>

            {error && (
              <Typography color='error' sx={{ mt: 2 }}>
                {error}
              </Typography>
            )}

            {generatedEmail && (
             <Box sx={{ mt: 3}}>
                <Typography variant='h6' gutterBottom>
                  Generated Reply:
                </Typography>
                <TextField
                  fullWidth
                  multiline
                  rows={6}
                  variant='outlined'
                  value={generatedEmail || ''}
                  inputProps={{ readOnly: true }}/>
              
              <Button
                variant='outlined'
                sx={{ mt: 2 }}
                onClick={() => navigator.clipboard.writeText(generatedEmail)}
                startIcon={<ContentCopy />}> 
                  Copy to Clipboard
              </Button>
             </Box> 
            )}
          </Paper>
        </Container>
      </Box>
    </ThemeProvider>
  );
}

export default App;
